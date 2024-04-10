package example.abhi;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;
import io.swagger.v3.oas.annotations.servers.Server;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.context.ApplicationContext;

@OpenAPIDefinition(
    info = @Info(
        title = "micronaut-guides",
        version = "1.0"
    ), servers = @Server(url = "http://localhost:8080")
)
public class Application {
  public static final String ASCII_ART = """
               _________                  .__
              /   _____/ ______________  _|__| ____  ____
              \\_____  \\_/ __ \\_  __ \\  \\/ /  |/ ___\\/ __ \\
              /        \\  ___/|  | \\/\\   /|  \\  \\__\\  ___/
             "/_______  /\\___  >__|    \\_/ |__|\\___  >___  >
                     \\/     \\/                    \\/    \\/
            """;
  public static void main(String[] args) {
    ApplicationContext ctx = Micronaut.run(Application.class, args);
    printDocLinks(ctx.getBean(EmbeddedServer.class));
    System.out.println(ASCII_ART);
  }

  private static void printDocLinks(EmbeddedServer embeddedServer) {
    String contextPath = embeddedServer.getEnvironment()
        .getProperty("micronaut.server.context-path", String.class)
        .orElse("");
    System.out.println("==============================================================");
    System.out.printf("SwaggerUI: %s%s/swagger-ui/index.html%n", embeddedServer.getURI(), contextPath);
    System.out.printf("OpenAPI: %s%s/swagger/micronaut-guides-1.0.yml%n",
        embeddedServer.getURI(), contextPath);
    System.out.println("==============================================================");

  }
}