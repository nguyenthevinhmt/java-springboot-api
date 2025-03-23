package rest_api_app.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import rest_api_app.Common.Api.ApiResponse;
import rest_api_app.Common.Exception.ErrorCode;
import rest_api_app.Common.Exception.UserFriendlyException;
import rest_api_app.Dtos.CreateProductDto;
import rest_api_app.Dtos.ProductPagingRequestDto;
import rest_api_app.Models.Product;
import rest_api_app.Repository.ProductRepository;
import rest_api_app.Services.ProductService;

@RestController
@RequestMapping(path = "/api/v1/product")
public class ProductController {
    @Autowired
    private ProductRepository repository;
    private ProductService productService;

    @GetMapping(path = "/find-all")
    public ApiResponse FindAll( ProductPagingRequestDto input) {
        Pageable pageable = PageRequest.of(
                input.getPageIndex(),
                input.getPageSize(),
                Sort.by("id").descending()
        );

//        Specification<Product> spec = Specification.where(null);
//
//        if (StringUtils.hasText(input.getKeyWord())) {
//            spec = spec.and((root, query, cb) -> {
//                String keyword = "%" + input.getKeyWord().toLowerCase() + "%";
//                return cb.or(
//                        cb.like(cb.lower(root.get("name")), keyword)
//                );
//            });
//        }
        var pageResult = repository.findAll(pageable);
        return new ApiResponse(pageResult);
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
