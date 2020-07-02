package ksaito.practice.jwt.contoller;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;

import ksaito.practice.jwt.request.UserCreateRequest;
import ksaito.practice.jwt.response.UserGetResponse;
import ksaito.practice.jwt.service.UserCreateService;
import ksaito.practice.jwt.service.UserGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpEntity.EMPTY;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/user")
public class UserController {
  @Autowired
  private UserCreateService userCreateService;
  @Autowired
  private UserGetService userGetService;

  @RequestMapping(value = "/create", method = POST)
  public ResponseEntity create(@RequestBody UserCreateRequest request) {
    userCreateService.create(request);
    return ResponseEntity.ok(EMPTY);
  }

  @RequestMapping(value = "/get", method = GET)
  public ResponseEntity<UserGetResponse> get(HttpServletRequest request) {
    return ResponseEntity.ok(userGetService.get(request));
  }
}
