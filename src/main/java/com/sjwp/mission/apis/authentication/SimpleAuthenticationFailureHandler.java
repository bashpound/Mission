package com.sjwp.mission.apis.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.sjwp.mission.apis.result.ApiResult;
import com.sjwp.mission.utils.JsonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleAuthenticationFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                      AuthenticationException exception) throws IOException {
    response.setStatus(HttpStatus.BAD_REQUEST.value());
    ApiResult failure;
    if (exception instanceof BadCredentialsException) {
      failure = ApiResult.message("로그인 정보가 잘못되었습니다.");
    } else if (exception instanceof InsufficientAuthenticationException) {
      failure = ApiResult.message("권한이 충분하지 않습니다.");
    } else {
      failure = ApiResult.message("자격 증명에 실패하였습니다.");
    }
    JsonUtils.write(response.getWriter(), failure);
    
  }
}
