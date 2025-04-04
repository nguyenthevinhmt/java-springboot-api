package rest_api_app.Dtos;

import rest_api_app.Shared.Common.Api.PagingRequestBaseDto;

public class ProductPagingRequestDto extends PagingRequestBaseDto {
    ProductPagingRequestDto(int pageSize, int pageIndex,String keyWord) {
        super(pageSize, pageIndex, keyWord);
    }
}
