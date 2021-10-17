package br.com.luizalabs.favoriteproducts.product.mapper;

import br.com.luizalabs.favoriteproducts.product.domain.Product;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductPrice;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductReviewScore;
import br.com.luizalabs.favoriteproducts.product.entity.ProductEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapper {

    public static ProductEntity toEntity(final Product product) {
        return ProductEntity.builder()
            .id(product.getId().value())
            .title(product.getTitle())
            .brand(product.getBrand())
            .price(product.getPrice().value())
            .image(product.getImage())
            .reviewScore(product.getReviewScore().value())
            .build();
    }

    public static Product toDomain(final ProductEntity productEntity) {
        return new Product(
            ProductId.from(productEntity.getId().toString()),
            productEntity.getTitle(),
            productEntity.getBrand(),
            ProductPrice.from(productEntity.getPrice()),
            productEntity.getImage(),
            ProductReviewScore.from(productEntity.getReviewScore())
        );
    }

    public static Set<Product> parse(final Set<ProductEntity> productEntities) {
        return CollectionUtils.isEmpty(productEntities)
            ? Collections.emptySet()
            : productEntities.stream().map(ProductMapper::toDomain).collect(Collectors.toSet());
    }
}
