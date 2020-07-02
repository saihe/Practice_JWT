package ksaito.practice.jwt.request;

import lombok.Getter;

@Getter
public class AuthLoginRequest {
  private String loginId;
  private String password;
}
