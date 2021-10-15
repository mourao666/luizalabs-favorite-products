package br.com.luizalabs.favoriteproducts.product.domain;

import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductPrice;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductReviewScore;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @EqualsAndHashCode.Include
    private final ProductId id;
    private final String title;
    private final String brand;
    private final ProductPrice price;
    private final String image;
    private final ProductReviewScore reviewScore;

    public Product(final ProductId id, final String title, final String brand, final ProductPrice price,
                   final String image, final ProductReviewScore reviewScore) {
        this.id = id;
        this.title = title;
        this.brand = brand;
        this.price = price;
        this.image = image;
        this.reviewScore = reviewScore;
    }
}
