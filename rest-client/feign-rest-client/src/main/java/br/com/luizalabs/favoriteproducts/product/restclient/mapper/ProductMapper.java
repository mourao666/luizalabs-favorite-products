package br.com.luizalabs.favoriteproducts.product.restclient.mapper;

import br.com.luizalabs.favoriteproducts.product.domain.Product;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductPrice;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductReviewScore;
import br.com.luizalabs.favoriteproducts.product.restclient.dto.ProductResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapper {

    public static Product toDomain(final ProductResponse productResponse) {
        return new Product(
            ProductId.from(productResponse.getId()),
            productResponse.getTitle(),
            productResponse.getBrand(),
            ProductPrice.from(productResponse.getPrice()),
            productResponse.getImage(),
            ProductReviewScore.from(productResponse.getReviewScore())
        );
    }
}
