package umc.hongik_hospital.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
public class JwtUtil {

    private final String secret; // JWT 서명에 사용되는 비밀키
    public JwtUtil(@Value("${jwt.secret}") String secret) {
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }

    // JWT 토큰 생성
    public String generateToken(String username) {
        long expirationTimeInMillis = Duration.ofHours(10).toMillis();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMillis))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // 토큰에서 사용자 이름 추출
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // 토큰 만료 여부 확인
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // 토큰 검증
    public boolean validateToken(String token, String username) {
        try {
            String extractedUsername = extractUsername(token);
            return username.equals(extractedUsername) && !isTokenExpired(token);
        } catch (Exception e) {
            return false; // 토큰이 올바르지 않을 경우 false 반환
        }
    }

    // Claims 추출
    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("JWT 토큰이 유효하지 않습니다.", e);
        }
    }
}
