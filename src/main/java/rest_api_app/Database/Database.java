package rest_api_app.Database;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rest_api_app.Models.Product;
import rest_api_app.Repository.ProductRepository;

@Configuration
public class Database {
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product product1 = new Product("Sản phẩm 1", 10000.0, "/api/v1/product/1", 2025);
                Product product2 = new Product("Sản phẩm 2", 10000.0, "/api/v1/product/1", 2025);
                productRepository.save(product1);
                productRepository.save(product2);
            }
        };
    }
}
