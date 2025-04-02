package rest_api_app.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import rest_api_app.Common.Api.ApiResponse;
import rest_api_app.Common.Api.PagingResultDto;
import rest_api_app.Common.Exception.ErrorCode;
import rest_api_app.Common.Exception.UserFriendlyException;
import rest_api_app.Dtos.CreateProductDto;
import rest_api_app.Dtos.ProductDto;
import rest_api_app.Dtos.ProductPagingRequestDto;
import rest_api_app.Models.Product;
import rest_api_app.Repository.ProductRepository;
import rest_api_app.Services.ProductService;

import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductController {
    private final ProductRepository repository;
    private final ProductService productService;

    public ProductController(ProductRepository repository, ProductService productService) {
        this.productService = productService;
        this.repository = repository;
    }


    @PostMapping(path = "/find-all-paging")
    public ApiResponse findAll(@RequestBody Map<String, String> body) {
        Page<ProductDto> productDtos = this.productService.PagingAndFilter(body);
        PagingResultDto<ProductDto> pagingResult = new PagingResultDto<ProductDto>(
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
    public void Create(@RequestBody CreateProductDto input) {
        Product newProduct = new Product(input.getName(), input.getPrice(), input.getUrl(), input.getProductYear());
        repository.save(newProduct);
    }
}
