package pl.zbrogdom.csspatternapp.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtService jwtService;

    public JwtAuthenticationSuccessHandler(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String signedJWT = jwtService.createSignedJWT(authentication.getName());
        new ObjectMapper().writeValue(response.getWriter(), new JwtWrapper(signedJWT));
    }

    private record JwtWrapper(String token){ }
}
