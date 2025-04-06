package rest_api_app.Config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "JWT Bearer",
        type = SecuritySchemeType.HTTP,
        scheme = "Bearer",
        bearerFormat = "JWT",
        description = "Bearer token authentication"
)

public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Restful API App")
                        .version("1.0")
                        .description("Tự học Java Spring Boot"))
                .addSecurityItem(new SecurityRequirement().addList("JWT Bearer"));
    }
}
