package arqui.hexa_core.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

public class SwaggerApiConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Hexagonal - POC")
                        .description("API de backend para demonstrar arquitetura hexagonal - POC")
                        .version("v1")
                        .contact(new Contact()
                                .name("Seu Nome")
                                .email("email@teste.com")));
    }
}
