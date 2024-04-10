package example.abhi.base;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Serdeable
public record Option(@NotNull Language language,
                     @NotNull BuildTool buildTool,
                     @NotBlank String url) {
}
