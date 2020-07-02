package ksaito.practice.jwt.service;

import javax.servlet.http.HttpServletRequest;

import ksaito.practice.jwt.response.UserGetResponse;

public interface UserGetService {
  UserGetResponse get(HttpServletRequest request);
}
