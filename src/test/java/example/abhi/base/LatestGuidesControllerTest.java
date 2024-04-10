package example.abhi.base;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.BlockingHttpClient;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
class LatestGuidesControllerTest {

  @Test
  void guidesEndpoint(@Client("/") HttpClient httpClient) {
    BlockingHttpClient client = httpClient.toBlocking();
    URI uri = UriBuilder.of("/latest").path("guides.json").build();
    HttpRequest<?> request = HttpRequest.GET(uri);
    String json = assertDoesNotThrow(() -> client.retrieve(request));
    assertTrue(json.contains("2018-05-23"));
  }
}