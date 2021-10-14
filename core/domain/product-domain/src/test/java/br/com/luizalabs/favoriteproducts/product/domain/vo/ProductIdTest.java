package br.com.luizalabs.favoriteproducts.product.domain.vo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductIdTest {

    @Test
    public void shouldThrowsIllegalArgumentExceptionWhenANewIdIsCreatedWithNullValue() {
        assertThrows(IllegalArgumentException.class, () -> ProductId.from(null));
    }

    @Test
    public void shouldThrowsIllegalArgumentExceptionWhenANewIdIsCreatedWithEmptyValue() {
        assertThrows(IllegalArgumentException.class, () -> ProductId.from(""));
    }

    @Test
    public void shouldThrowsIllegalArgumentExceptionWhenANewIdIsCreatedWithBlankValue() {
        assertThrows(IllegalArgumentException.class, () -> ProductId.from(" "));
    }

    @Test
    public void shouldBeCreatedWithValidValueSuccessfully() {
        final ProductId id = ProductId.from("123e4567-e89b-42d3-a456-556642440000");
        assertNotNull(id);
        assertNotNull(id.value());
    }
}
