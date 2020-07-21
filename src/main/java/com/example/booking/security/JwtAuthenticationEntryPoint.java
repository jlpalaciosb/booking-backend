package com.example.booking.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException authEx) throws IOException, ServletException {
        if (authEx.getMessage().equals("Bad credentials")) {
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad credentials");
        } else {
            httpServletResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
        }
    }
}
