package ksaito.practice.jwt.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthLoginResponse {
  private String token;
}
