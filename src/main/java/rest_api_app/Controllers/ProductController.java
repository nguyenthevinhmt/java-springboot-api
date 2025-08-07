package rest_api_app.Controllers;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import rest_api_app.Common.Api.ApiResponse;
import rest_api_app.Common.Api.PagingResultDto;
import rest_api_app.Common.Exception.ErrorCode;
import rest_api_app.Common.Exception.UserFriendlyException;
import rest_api_app.Dtos.CreateProductDto;
import rest_api_app.Dtos.ProductDto;
import rest_api_app.Dtos.ProductPagingRequestDto;
import rest_api_app.Entity.Product.Product;
import rest_api_app.Repository.Product.ProductRepository;
import rest_api_app.Services.IProductService;

@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductController {
    private final ProductRepository repository;
    private final IProductService productService;

    public ProductController(ProductRepository repository, IProductService productService) {
        this.productService = productService;
        this.repository = repository;
    }

    @PermitAll
    @PostMapping(path = "/find-all-paging")
    public ApiResponse findAll(@RequestBody ProductPagingRequestDto body) {
        Page<ProductDto> productDtos = this.productService.PagingAndFilterV2(body);
        PagingResultDto<ProductDto> pagingResult = new PagingResultDto<>(
                productDtos.getContent(),
                productDtos.getTotalElements()
        );
        return new ApiResponse(pagingResult);
    }

    @GetMapping(path = "/find-by-id/{id}")
    public Product FindById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new UserFriendlyException(ErrorCode.ProductNotFound));
    }

    @PostMapping(path = "/create")
    public void Create(@Valid @RequestBody CreateProductDto input) {
        Product newProduct = Product.builder()
                .name(input.getName())
                .price(input.getPrice())
                .productYear(input.getProductYear())
                .url(input.getUrl())
                .build();
        repository.save(newProduct);
    }
}
