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
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String signedJWT = jwtService.createSignedJWT(userDetails.getEmail());
        ResponseWrapper body = new ResponseWrapper(signedJWT, userDetails.getUsername(), userDetails.getId(), userDetails.getEmail(), userDetails.getHomepage());
        new ObjectMapper().writeValue(response.getWriter(), body);
    }

    private record ResponseWrapper(String token, String username, long id, String email, String homepage){ }
}
