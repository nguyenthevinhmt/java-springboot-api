package rest_api_app.Entity.Product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String url;
    private int productYear;

    public Product() {
    }

    public Product(String name, Double price, String url, int productYear) {
        this.name = name;
        this.price = price;
        this.url = url;
        this.productYear = productYear;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" +  this.name + '\'' +
                ", price=" +  this.price +
                ", url='" +  this.url + '\'' +
                ", productYear=" +  this.productYear +
                '}';
    }
}
