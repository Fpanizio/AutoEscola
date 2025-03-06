package panizio.DrivingSchool.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Auto Escola API",
        version = "1.0.0",
        description = "Documentação da API para gestão de funcionários."
    )
)
public class OpenApiConfig {
}