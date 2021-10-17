package br.com.luizalabs.favoriteproducts.customer.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.CustomerStatus;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.usecase.exception.CustomerNotFoundException;
import br.com.luizalabs.favoriteproducts.customer.usecase.port.Customers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DeleteCustomerTest {

    private static final CustomerId DEFAULT_ID = CustomerId.from("123e4567-e89b-42d3-a456-556642440000");
    private static final Customer CUSTOMER = new Customer(DEFAULT_ID, "Customer Name", "customer.name@test.com", CustomerStatus.ACTIVE);

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
        when(customers.find(any(CustomerId.class))).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> deleteCustomer.delete(DEFAULT_ID));
    }

    @Test
    public void shouldBeDeleteCustomerWithSuccess() {
        when(customers.find(any(CustomerId.class))).thenReturn(Optional.of(CUSTOMER));
        deleteCustomer.delete(DEFAULT_ID);
    }
}
