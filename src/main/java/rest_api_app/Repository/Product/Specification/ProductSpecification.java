package rest_api_app.Repository.Product.Specification;

import org.springframework.data.jpa.domain.Specification;
import rest_api_app.Entity.Product.Product;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<Product> getSpecification(String name) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (name != null) {
                predicates.add(criteriaBuilder.equal(root.get("name"), name));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
