package ksaito.practice.jwt.contoller;

import ksaito.practice.jwt.request.AuthLoginRequest;
import ksaito.practice.jwt.response.AuthLoginResponse;
import ksaito.practice.jwt.service.AuthLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
  @Autowired
  private AuthLoginService authLoginService;

  @RequestMapping(value = "/login", method = POST)
  public ResponseEntity<AuthLoginResponse> login(@RequestBody AuthLoginRequest request) {
    return ResponseEntity.ok(authLoginService.authentication(request));
  }
}
