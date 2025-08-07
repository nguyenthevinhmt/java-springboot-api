package rest_api_app.Services;

import org.springframework.data.domain.Page;
import rest_api_app.Dtos.ProductDto;
import rest_api_app.Dtos.ProductPagingRequestDto;

public interface IProductService {
    Page<ProductDto> PagingAndFilter(ProductPagingRequestDto params);
    Page<ProductDto> PagingAndFilterV2(ProductPagingRequestDto params);

}
