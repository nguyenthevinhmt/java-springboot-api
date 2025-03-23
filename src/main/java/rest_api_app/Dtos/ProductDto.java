package rest_api_app.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDto {
    private long id;
    private String name;
    private Double price;
    private String url;
    private int productYear;
}
