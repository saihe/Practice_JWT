package ksaito.practice.jwt.service.impl;

import java.sql.Date;
import java.sql.Timestamp;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ksaito.practice.jwt.entity.Token;
import ksaito.practice.jwt.repository.TokenRepository;
import ksaito.practice.jwt.repository.UserRepository;
import ksaito.practice.jwt.request.AuthLoginRequest;
import ksaito.practice.jwt.response.AuthLoginResponse;
import ksaito.practice.jwt.service.AuthLoginService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class AuthLoginServiceImpl implements AuthLoginService {
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  @Autowired
  private TokenRepository tokenRepository;
  @Autowired
  private UserRepository userRepository;

  @Override
  public AuthLoginResponse authentication(AuthLoginRequest request) {
    val user = userRepository.findByLoginId(request.getLoginId()).orElseThrow(() ->
      new HttpServerErrorException(HttpStatus.NOT_FOUND));
    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
    }

    Token token = Token.builder()
      .type("JWT")
      .algorithm(SignatureAlgorithm.HS256.getValue())
      .userId(user.getId())
      .issuer("ksaito")
      .subject("JWTの練習")
      .name(user.getLoginId())
      .issuerAt(String.valueOf(new Timestamp(System.currentTimeMillis())))
      .expireAt(String.valueOf(new Timestamp(System.currentTimeMillis() + (10 * 60 * 1000))))
      .createdAt(new Timestamp(System.currentTimeMillis()))
      .updatedAt(new Timestamp(System.currentTimeMillis()))
      .build();
    tokenRepository.save(token);

    String jwt = Jwts.builder()
      .setSubject(token.getSubject())
      .setId(token.getId().toString())
      .setIssuedAt(new Date(Timestamp.valueOf(token.getIssuerAt()).getTime()))
      .setNotBefore(new Date(Timestamp.valueOf(token.getIssuerAt()).getTime()))
      .setExpiration(new Date(Timestamp.valueOf(token.getExpireAt()).getTime()))
      .signWith(SignatureAlgorithm.HS256, "secret".getBytes())
      .compact();
    return AuthLoginResponse.builder()
      .token(jwt)
      .build();
  }
}
