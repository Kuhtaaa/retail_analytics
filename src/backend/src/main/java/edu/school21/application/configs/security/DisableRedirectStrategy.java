package edu.school21.application.configs.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.RedirectStrategy;

public class DisableRedirectStrategy implements RedirectStrategy {

    @Override
    public void sendRedirect(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final String url
    ) {
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Location", url);
    }
}
