package br.com.luizalabs.favoriteproducts.customer.domain.vo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerIdTest {

    @Test
    public void shouldBeCreatedANewIdWithSuccess() {
        final CustomerId id = CustomerId.newId();
        assertNotNull(id);
        assertNotNull(id.value());
    }

    @Test
    public void shouldThrowsIllegalArgumentExceptionWhenANewIdIsCreatedWithNullValue() {
        assertThrows(IllegalArgumentException.class, () -> CustomerId.from(null));
    }

    @Test
    public void shouldThrowsIllegalArgumentExceptionWhenANewIdIsCreatedWithEmptyValue() {
        assertThrows(IllegalArgumentException.class, () -> CustomerId.from(""));
    }

    @Test
    public void shouldThrowsIllegalArgumentExceptionWhenANewIdIsCreatedWithBlankValue() {
        assertThrows(IllegalArgumentException.class, () -> CustomerId.from(" "));
    }

    @Test
    public void shouldBeCreatedWithValidValueSuccessfully() {
        final CustomerId id = CustomerId.from("123e4567-e89b-42d3-a456-556642440000");
        assertNotNull(id);
        assertNotNull(id.value());
    }
}
