package rest_api_app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import rest_api_app.Dtos.ProductPagingRequestDto;
import rest_api_app.Models.Product;
import rest_api_app.Repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> findAll(ProductPagingRequestDto request) {
        Pageable pageable = PageRequest.of(
                request.getPageIndex(),
                request.getPageSize(),
                Sort.by("id").descending()
        );

        // Tạo Specification cho các điều kiện tìm kiếm
        Specification<Product> spec = Specification.where(null);

        if (StringUtils.hasText(request.getKeyWord())) {
            spec = spec.and((root, query, cb) -> {
                String keyword = "%" + request.getKeyWord().toLowerCase() + "%";
                return cb.or(
                        cb.like(cb.lower(root.get("name")), keyword)
                );
            });
        }

        return productRepository.findAll(pageable);
    }
}
