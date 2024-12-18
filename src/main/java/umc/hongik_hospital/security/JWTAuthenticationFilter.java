package umc.hongik_hospital.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import umc.hongik_hospital.util.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authorizationHeader);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // "Bearer " 부분을 한 번만 제거
            String token = authorizationHeader.replaceFirst("Bearer ", "").trim();
            System.out.println("Extracted Token: " + token);

            try {
                Long userId = jwtUtil.extractUserId(token);
                System.out.println("Extracted User ID: " + userId);

                if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    if (jwtUtil.validateToken(token, userId)) {
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(userId, null, null);
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        System.out.println("Authentication set for user ID: " + userId);
                    } else {
                        System.out.println("Token validation failed");
                    }
                }
            } catch (Exception e) {
                System.out.println("Token validation error: " + e.getMessage());
            }
        } else {
            System.out.println("Authorization header missing or invalid format");
        }

        filterChain.doFilter(request, response);
    }

}

