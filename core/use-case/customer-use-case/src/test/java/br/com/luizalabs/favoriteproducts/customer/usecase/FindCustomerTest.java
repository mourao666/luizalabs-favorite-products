package br.com.luizalabs.favoriteproducts.customer.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.usecase.exception.CustomerNotFoundException;
import br.com.luizalabs.favoriteproducts.customer.usecase.port.Customers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindCustomerTest {

    private static final CustomerId DEFAULT_ID = CustomerId.from("123e4567-e89b-42d3-a456-556642440000");
    private static final String DEFAULT_NAME = "Customer Name";
    private static final String DEFAULT_EMAIL = "customer.name@test.com";

    @Mock
    private Customers customers;

    @InjectMocks
    private FindCustomer findCustomer;

    // region find one

    @Test
    public void shouldBeThrowsNullPointerExceptionWhenACustomerIsSearchedWithNullId() {
        assertThrows(NullPointerException.class, () -> findCustomer.findOne(null));
    }

    @Test
    public void shouldBeThrowsCustomerNotFoundExceptionWhenACustomerIsSearchedWithInvalidId() {
        when(customers.find(any(CustomerId.class))).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> findCustomer.findOne(DEFAULT_ID));
    }

    @Test
    public void shouldBeFindCustomerWithSuccess() {

        final Customer customer = new Customer(DEFAULT_NAME, DEFAULT_EMAIL);

        when(customers.find(any(CustomerId.class))).thenReturn(Optional.of(customer));

        final Customer foundCustomer = findCustomer.findOne(customer.getId());
        assertNotNull(foundCustomer);
    }

    // endregion
}
