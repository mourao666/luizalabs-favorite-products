package br.com.luizalabs.favoriteproducts.product.domain.vo;

import lombok.EqualsAndHashCode;
import java.util.Objects;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductReviewScore {

    @EqualsAndHashCode.Include
    private final Double reviewScore;

    private ProductReviewScore(final Double reviewScore) {
        this.reviewScore = reviewScore;
    }

    public static ProductReviewScore from(final Double reviewScore) {

        if (!Objects.isNull(reviewScore) && reviewScore < 0.0d) {
            throw new IllegalArgumentException("The review score cannot be negative");
        }

        return new ProductReviewScore(reviewScore);
    }

    public Double value() {
        return this.reviewScore;
    }

    @Override
    public String toString() {
        return Objects.isNull(reviewScore) ? "" : String.format("%.1f", this.reviewScore);
    }
}
