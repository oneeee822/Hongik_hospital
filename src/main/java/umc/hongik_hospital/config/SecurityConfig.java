package umc.hongik_hospital.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // CSRF 보호 비활성화 (필요 시 활성화)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/patients/**","/hospitals/**").permitAll() //경로 허용
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/error").permitAll() // Swagger 허용
                        .anyRequest().authenticated() // 그 외의 요청은 인증 필요
                )
                .formLogin(AbstractHttpConfigurer::disable); // 기본 로그인 폼 비활성화 (JWT 인증 사용 시)

        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager(); // 인증 매니저 설정
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화에 사용
    }
}
