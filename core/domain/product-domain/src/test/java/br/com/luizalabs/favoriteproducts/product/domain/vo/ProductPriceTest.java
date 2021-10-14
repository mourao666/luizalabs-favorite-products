package br.com.luizalabs.favoriteproducts.product.domain.vo;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductPriceTest {

    @Test
    public void shouldThrowsIllegalArgumentExceptionWhenANewPriceIsCreatedWithPrimitiveNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> ProductPrice.from(-1.0d));
    }

    @Test
    public void shouldBeCreatedANewPriceWithPrimitiveSuccessfully() {

        final ProductPrice priceZero = ProductPrice.from(0.0d);
        assertNotNull(priceZero);
        assertNotNull(priceZero.value());
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), priceZero.value());

        final ProductPrice priceOne = ProductPrice.from(1.0d);
        assertNotNull(priceOne);
        assertNotNull(priceOne.value());
        assertEquals(BigDecimal.ONE.setScale(2, RoundingMode.HALF_UP), priceOne.value());
    }

    @Test
    public void shouldThrowsIllegalArgumentExceptionWhenANewPriceIsCreatedWithNullValue() {
        assertThrows(IllegalArgumentException.class, () -> ProductPrice.from(null));
    }

    @Test
    public void shouldThrowsIllegalArgumentExceptionWhenANewPriceIsCreatedWithNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> ProductPrice.from(BigDecimal.valueOf(-1.0d)));
    }

    @Test
    public void shouldThrowsIllegalArgumentExceptionWhenANewIdIsCreatedWithBlankValue() {
        assertThrows(IllegalArgumentException.class, () -> ProductId.from(" "));
    }

    @Test
    public void shouldBeCreatedANewPriceSuccessfully() {

        final ProductPrice priceZero = ProductPrice.from(BigDecimal.valueOf(0.0d));
        assertNotNull(priceZero);
        assertNotNull(priceZero.value());
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), priceZero.value());

        final ProductPrice priceOne = ProductPrice.from(BigDecimal.valueOf(1.0d));
        assertNotNull(priceOne);
        assertNotNull(priceOne.value());
        assertEquals(BigDecimal.ONE.setScale(2, RoundingMode.HALF_UP), priceOne.value());
    }
}
