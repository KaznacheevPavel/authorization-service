package ru.kaznacheev.authservice.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import java.util.UUID;

@Configuration
public class SynchronizedCsrfTokenRepository implements CsrfTokenRepository {

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String token = UUID.randomUUID().toString();
        CsrfToken csrfToken = new DefaultCsrfToken("X-CSRF-TOKEN", "_csrf", token);
        request.getSession().setAttribute("CSRF_TOKEN", csrfToken);
        return csrfToken;
    }

    @Override
    public void saveToken(CsrfToken token, HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getSession().getAttribute("CSRF_TOKEN");
        request.getSession().removeAttribute("CSRF_TOKEN");
        return token;
    }

}
