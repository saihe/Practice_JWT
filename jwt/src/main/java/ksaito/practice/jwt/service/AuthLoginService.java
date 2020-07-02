package ksaito.practice.jwt.service;

import ksaito.practice.jwt.request.AuthLoginRequest;
import ksaito.practice.jwt.response.AuthLoginResponse;

public interface AuthLoginService {
  AuthLoginResponse authentication(AuthLoginRequest request);
}
