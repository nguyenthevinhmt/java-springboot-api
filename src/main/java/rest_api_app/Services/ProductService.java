package rest_api_app.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import rest_api_app.Dtos.ProductDto;
import rest_api_app.Models.Product;
import rest_api_app.Repository.ProductRepository;
import rest_api_app.Repository.Specification.ProductSpecification;

import java.util.Map;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<ProductDto> PagingAndFilter(Map<String, String> params){
        int pageIndex = params.get("page_index") != null ? Integer.parseInt(params.get("page_index")) : 1;
        int pageSize = params.get("page_size") != null ? Integer.parseInt(params.get("page_size")) : 25;
        PageRequest pageable = PageRequest.of(pageIndex,pageSize);

        params.remove("page_index");
        params.remove("page_size");

        String name = params.get("name");
        Specification<Product> specification = ProductSpecification.getSpecification(name);

        final Page<Product> products = this.productRepository.findAll(specification,pageable);
        return products.map(p -> new ProductDto(p));
    }
}








