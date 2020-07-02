package ksaito.practice.jwt.service.impl;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import ksaito.practice.jwt.entity.User;
import ksaito.practice.jwt.repository.TokenRepository;
import ksaito.practice.jwt.repository.UserRepository;
import ksaito.practice.jwt.response.UserGetResponse;
import ksaito.practice.jwt.service.UserGetService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

@Service
public class UserGetServiceImpl implements UserGetService {
  @Autowired
  private TokenRepository tokenRepository;
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserGetResponse get(HttpServletRequest request) {
    AtomicInteger userId = new AtomicInteger();
    val header = Optional.ofNullable(request.getHeader(HttpHeaders.AUTHORIZATION));
    header.map(h -> h.split(" ")).ifPresent(arr -> {
        if (!arr[0].startsWith("Bearer")) {
          throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        }
        val jwt = Jwts.parser()
          .setSigningKey("secret".getBytes())
          .parseClaimsJws(arr[1]);
        if (!jwt.getHeader().getAlgorithm().equals(HS256.getValue())) {
          throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
        }
        val body = jwt.getBody();
        val token = tokenRepository.findById(Integer.parseInt(body.getId())).orElseThrow(() ->
          new HttpServerErrorException(HttpStatus.BAD_REQUEST)
        );
        userId.set(token.getUserId());
      }
    );

    val user = userRepository.findById(userId.get()).orElseThrow(() ->
      new HttpServerErrorException(HttpStatus.BAD_REQUEST)
    );

    return UserGetResponse.builder()
      .loginId(user.getLoginId())
      .build();
  }
}
