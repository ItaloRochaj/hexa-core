package arqui.hexa_core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Hexagonal - POC")
                        .description("Projeto demonstrando arquitetura hexagonal e SOLID")
                        .version("1.0")
                        .contact(new Contact().name("Seu Nome").email("email@teste.com"))
                );
    }
}
