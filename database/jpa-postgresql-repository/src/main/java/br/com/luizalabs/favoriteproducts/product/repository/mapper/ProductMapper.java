package br.com.luizalabs.favoriteproducts.product.repository.mapper;

import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.product.domain.Product;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductPrice;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductReviewScore;
import br.com.luizalabs.favoriteproducts.product.repository.entity.ProductEntity;
import br.com.luizalabs.favoriteproducts.product.repository.entity.ProductEntityId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductMapper {

    public static ProductEntity toEntity(final Product product, final CustomerId customerId) {
        return ProductEntity.builder()
            .id(new ProductEntityId(product.getId().value(), customerId.value()))
            .title(product.getTitle())
            .brand(product.getBrand())
            .price(product.getPrice().value())
            .image(product.getImage())
            .reviewScore(product.getReviewScore().value())
            .build();
    }

    public static Product toDomain(final ProductEntity productEntity) {
        return new Product(
            ProductId.from(productEntity.getId().getProductId().toString()),
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
