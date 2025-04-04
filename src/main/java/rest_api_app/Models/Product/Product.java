package rest_api_app.Models.Product;

import rest_api_app.Shared.EntityBase.EntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
public class Product extends EntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;
    @Column(name = "PRICE", nullable = false)
    private Double price;
    @Column(name = "URL", nullable = true, length = 512)
    private String url;
    @Column(name = "PRODUCT_YEAR")
    private int productYear;

    public Product(String name, Double price, String url, int productYear) {
        this.name = name;
        this.price = price;
        this.url = url;
        this.productYear = productYear;
    }
}
