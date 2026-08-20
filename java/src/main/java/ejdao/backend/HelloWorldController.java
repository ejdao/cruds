package ejdao.backend;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Hello World", description = "Hello world operations")
public class HelloWorldController {

  @Operation(
    summary = "Get a hello world message",
    description = "Returns a simple hello world text response.",
    responses = {
      @ApiResponse(
        responseCode = "200",
        description = "Hello world message returned successfully",
        content = @Content(
          mediaType = MediaType.TEXT_PLAIN_VALUE,
          schema = @Schema(implementation = String.class)
        )
      ),
    }
  )
  @GetMapping(value = "/hello", produces = MediaType.TEXT_PLAIN_VALUE)
  public String helloWorld() {
    return "Hello, World!";
  }
}
