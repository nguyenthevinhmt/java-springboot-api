package rest_api_app.Services;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import rest_api_app.Dtos.ProductDto;
import rest_api_app.Dtos.ProductPagingRequestDto;
import rest_api_app.Entity.Product.Product;
import rest_api_app.Entity.Product.QProduct;
import rest_api_app.Repository.Product.ProductRepository;
import rest_api_app.Repository.Product.Specification.ProductSpecification;

import java.util.List;

@Service
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final JPAQueryFactory queryFactory;

    public ProductService( EntityManager entityManager, ProductRepository productRepository) {
        this.queryFactory = new JPAQueryFactory(entityManager);
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDto> PagingAndFilter(ProductPagingRequestDto params){
        int pageIndex = params.getPageIndex() - 1;
        int pageSize = params.getPageSize() != 0 ? params.getPageSize() : 25;
        PageRequest pageable = PageRequest.of(pageIndex,pageSize);

        String name = params.getKeyWord();
        Specification<Product> specification = ProductSpecification.getSpecification(name);

        final Page<Product> products = this.productRepository.findAll(specification,pageable);
        return products.map(ProductDto::new);
    }
    @Override
    public Page<ProductDto> PagingAndFilterV2(ProductPagingRequestDto params){
        QProduct product = QProduct.product;
        var filter = new BooleanBuilder();
        if(params.getKeyWord() != null && !params.getKeyWord().isEmpty()) {
            filter.and(QProduct.product.name.containsIgnoreCase(params.getKeyWord()));
        }
        Long total = queryFactory
                .select(product.count())
                .from(product)
                .where(filter)
                .fetchOne();

        List<ProductDto> results = queryFactory
                .selectFrom(product)
                .where(filter)
                .offset((long) params.getPageSize() * (params.getPageIndex() - 1))
                .limit(params.getPageSize())
                .orderBy(product.id.desc())
                .fetch()
                .stream()
                .map(ProductDto::new)
                .toList();
        return new PageImpl<ProductDto>(results, PageRequest.of(params.getPageIndex(), params.getPageSize()), total);
    }
}








