package br.com.luizalabs.favoriteproducts.product.domain.vo;

import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductPrice {

    @EqualsAndHashCode.Include
    private final BigDecimal price;

    private ProductPrice(BigDecimal price) {
        this.price = price.setScale(2, RoundingMode.HALF_UP);
    }

    public static ProductPrice from(final double price) {

        if (price < 0.0d) {
            throw new IllegalArgumentException("The price cannot be negative");
        }

        return new ProductPrice(BigDecimal.valueOf(price));
    }

    public static ProductPrice from(final BigDecimal price) {

        if (Objects.isNull(price)) {
            throw new IllegalArgumentException("The price cannot be null");
        }

        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("The price cannot be negative");
        }

        return new ProductPrice(price);
    }

    public BigDecimal value() {
        return this.price;
    }

    @Override
    public String toString() {
        return this.price.toString();
    }
}
