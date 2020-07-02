package ksaito.practice.jwt.request;

import lombok.Getter;

@Getter
public class UserCreateRequest {
  private String loginId;
  private String password;
}
