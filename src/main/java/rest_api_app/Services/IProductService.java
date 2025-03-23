package rest_api_app.Services;

import rest_api_app.Common.Api.PagingResultDto;
import rest_api_app.Dtos.ProductDto;
import rest_api_app.Dtos.ProductPagingRequestDto;

public interface IProductService {
    PagingResultDto<ProductDto> pagingAndFilter(ProductPagingRequestDto input);
}
