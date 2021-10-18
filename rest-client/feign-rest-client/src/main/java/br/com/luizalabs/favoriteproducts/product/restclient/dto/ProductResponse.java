package br.com.luizalabs.favoriteproducts.product.restclient.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ProductResponse {

    private String id;
    private String title;
    private String brand;
    private String image;
    private BigDecimal price;
    private Double reviewScore;
}
