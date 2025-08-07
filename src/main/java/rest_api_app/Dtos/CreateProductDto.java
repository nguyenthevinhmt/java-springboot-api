package rest_api_app.Dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
public class CreateProductDto {
    @NotNull(message = "Product name is required")
    @Size(min = 3, max = 20, message = "Product name must be between 3 and 20 characters")
    private String name;
    private Double price;
    private String url;
    private int productYear;
}
