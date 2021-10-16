package br.com.luizalabs.favoriteproducts.product.entity;

import br.com.luizalabs.favoriteproducts.customer.entity.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "customer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity {

    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String image;

    @Column
    private Double reviewScore;
}
