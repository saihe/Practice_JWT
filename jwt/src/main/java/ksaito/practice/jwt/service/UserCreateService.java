package ksaito.practice.jwt.service;

import ksaito.practice.jwt.request.UserCreateRequest;

public interface UserCreateService {
  void create(UserCreateRequest request);
}
