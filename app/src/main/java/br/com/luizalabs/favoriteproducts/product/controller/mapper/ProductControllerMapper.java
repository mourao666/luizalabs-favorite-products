package br.com.luizalabs.favoriteproducts.product.controller.mapper;

import br.com.luizalabs.favoriteproducts.product.controller.dto.ProductResponse;
import br.com.luizalabs.favoriteproducts.product.domain.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductControllerMapper {

    public static ProductResponse toDto(final Product product) {
        return ProductResponse.builder()
            .id(product.getId().toString())
            .title(product.getTitle())
            .brand(product.getBrand())
            .price(product.getPrice().value())
            .image(product.getImage())
            .reviewScore(product.getReviewScore().value())
            .build();
    }

    public static Set<ProductResponse> toDtoSet(final Collection<Product> productsCollection) {
        return CollectionUtils.isEmpty(productsCollection)
            ? Collections.emptySet()
            : productsCollection.stream()
                .map(ProductControllerMapper::toDto)
                .collect(Collectors.toSet());
    }
}
