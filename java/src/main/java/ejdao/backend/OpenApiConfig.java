package ejdao.backend;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI backendOpenAPI() {
    return new OpenAPI().info(
      new Info()
        .title("Backend API")
        .description("API documentation for the Java backend project.")
        .version("1.0.0")
    );
  }

  @Bean
  public GroupedOpenApi usersApi() {
    return group("users", "/users", "/users/**");
  }

  private GroupedOpenApi group(String name, String... paths) {
    return GroupedOpenApi.builder().group(name).pathsToMatch(paths).build();
  }
}
