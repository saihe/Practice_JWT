package ksaito.practice.jwt.service.impl;

import java.sql.Timestamp;

import ksaito.practice.jwt.entity.User;
import ksaito.practice.jwt.repository.UserRepository;
import ksaito.practice.jwt.request.UserCreateRequest;
import ksaito.practice.jwt.service.UserCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCreateServiceImpl implements UserCreateService {
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  @Autowired
  private UserRepository userRepository;

  @Override
  public void create(UserCreateRequest request) {
    userRepository.save(
      User.builder()
        .loginId(request.getLoginId())
        .password(passwordEncoder.encode(request.getPassword()))
        .createdAt(new Timestamp(System.currentTimeMillis()))
        .updatedAt(new Timestamp(System.currentTimeMillis()))
        .build()
    );
  }
}
