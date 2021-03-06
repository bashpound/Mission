package com.sjwp.mission.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.sjwp.mission.domain.model.member.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

  private final static Logger log = LoggerFactory.getLogger(AccessDeniedHandlerImpl.class);

  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
                     AccessDeniedException accessDeniedException) throws IOException {
    if (log.isDebugEnabled()) {
      log.debug("Access to `" + request.getRequestURI() + "` denied.");
    }

    if (request.getRequestURI().startsWith("/api/")) {
      if (request.getUserPrincipal() instanceof Member) {
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
      } else {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
      }
    } else {
      response.sendRedirect("/login");
    }
  }
}
