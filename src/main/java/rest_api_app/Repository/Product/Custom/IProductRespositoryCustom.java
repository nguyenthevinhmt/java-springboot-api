package rest_api_app.Repository.Product.Custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rest_api_app.Entity.Product.Product;

public interface IProductRespositoryCustom {
    Page<Product> searchWithPaging(String name, double price, Pageable pageable);
}
