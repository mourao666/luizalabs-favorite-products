package br.com.luizalabs.favoriteproducts.product.domain.vo;

import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductId {

    @EqualsAndHashCode.Include
    private final UUID id;

    private ProductId(final String id) {
        this.id = UUID.fromString(id);
    }

    public static ProductId from(final String id) {

        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("The id cannot be empty");
        }

        return new ProductId(id);
    }

    public UUID value() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.id.toString();
    }
}
