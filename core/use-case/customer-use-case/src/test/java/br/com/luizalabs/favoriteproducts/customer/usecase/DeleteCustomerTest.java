package br.com.luizalabs.favoriteproducts.customer.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.usecase.exception.CustomerNotFoundException;
import br.com.luizalabs.favoriteproducts.customer.usecase.port.Customers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteCustomerTest {

    private static final CustomerId DEFAULT_ID = CustomerId.from("123e4567-e89b-42d3-a456-556642440000");

    @Mock
    private Customers customers;

    @InjectMocks
    private DeleteCustomer deleteCustomer;

    @Test
    public void shouldBeThrowsNullPointerExceptionWhenACustomerIsDeletedWithNullId() {
        assertThrows(NullPointerException.class, () -> deleteCustomer.delete(null));
    }

    @Test
    public void shouldBeThrowsCustomerNotFoundExceptionWhenACustomerIsSearchedWithInvalidId() {
        when(customers.exists(any(CustomerId.class))).thenReturn(Boolean.FALSE);
        assertThrows(CustomerNotFoundException.class, () -> deleteCustomer.delete(DEFAULT_ID));
    }

    @Test
    public void shouldBeFindCustomerWithSuccess() {
        when(customers.exists(any(CustomerId.class))).thenReturn(Boolean.TRUE);
        deleteCustomer.delete(DEFAULT_ID);
    }
}
