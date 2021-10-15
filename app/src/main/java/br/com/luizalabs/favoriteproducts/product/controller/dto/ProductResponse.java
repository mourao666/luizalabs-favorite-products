package br.com.luizalabs.favoriteproducts.product.controller.dto;

import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Builder
public class ProductResponse {

    private String id;
    private String title;
    private String brand;
    private BigDecimal price;
    private String image;
    private Double reviewScore;
}
