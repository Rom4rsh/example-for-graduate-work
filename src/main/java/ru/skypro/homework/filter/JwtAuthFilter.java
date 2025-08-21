package ru.skypro.homework.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.utils.JwtToken;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtToken jwtToken;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public JwtAuthFilter(JwtToken jwtToken, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtToken = jwtToken;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // разрешаем OPTIONS сразу (preflight)
        if (HttpMethod.OPTIONS.matches(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        String jwt = extractJwt(request);

        if (StringUtils.hasText(jwt) && jwtToken.validateToken(jwt)) {
            String username = jwtToken.getUsername(jwt); // строка username

            // создаём Authentication с username как principal
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(username, null, null);
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }

    private String extractJwt(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (!StringUtils.hasText(authHeader)) return null;

        // Bearer
        if (authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }

        // Basic
        if (authHeader.startsWith("Basic ")) {
            try {
                String base64Creds = authHeader.substring(6);
                String creds = new String(Base64.getDecoder().decode(base64Creds), StandardCharsets.UTF_8);
                String[] parts = creds.split(":", 2);
                if (parts.length != 2) return null;

                String username = parts[0];
                String password = parts[1];

                User user = userRepository.findByUsername(username).orElse(null);
                if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                    // генерируем JWT для дальнейшего использования
                    return jwtToken.generateToken(user.getUsername());
                }
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }
}
