package br.com.luizalabs.favoriteproducts.product.domain.vo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductReviewScoreTest {

    @Test
    public void shouldThrowsIllegalArgumentExceptionWhenANewReviewScoreIsCreatedWithNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> ProductReviewScore.from(-1.0d));
    }

    @Test
    public void shouldBeCreatedANewReviewScoreSuccessfully() {

        final ProductReviewScore nullReviewScore = ProductReviewScore.from(null);
        assertNotNull(nullReviewScore);
        assertNull(nullReviewScore.value());

        final ProductReviewScore reviewScore = ProductReviewScore.from(1.0d);
        assertNotNull(reviewScore);
        assertNotNull(reviewScore.value());
        assertEquals(1.0d, reviewScore.value());
    }
}
