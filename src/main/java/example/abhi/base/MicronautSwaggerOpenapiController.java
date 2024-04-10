package example.abhi.base;

import io.micronaut.http.annotation.*;

@Controller("/micronaut-swagger-openapi")
public class MicronautSwaggerOpenapiController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}