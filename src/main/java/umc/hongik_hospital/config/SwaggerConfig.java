package umc.hongik_hospital.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI umcStudyAPI() {
        Info info = new Info()
                .title("UMC Server WorkBook API")
                .description("UMC Server WorkBook API 명세서")
                .version("1.0.0");

        String jwtSchemeName = "BearerAuth";

        // SecurityRequirement 설정 (API 요청 시 인증 정보 필요)
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);

        // SecurityScheme 등록 (Bearer 토큰 설정)
        SecurityScheme securityScheme = new SecurityScheme()
                .name(jwtSchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER);

        return new OpenAPI()
                .info(info)
                .components(new Components().addSecuritySchemes(jwtSchemeName, securityScheme))
                .addSecurityItem(securityRequirement);
    }
}
