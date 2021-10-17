package br.com.luizalabs.favoriteproducts.customer.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.CustomerStatus;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.usecase.exception.CustomerEmailAlreadyExistsException;
import br.com.luizalabs.favoriteproducts.customer.usecase.port.Customers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCustomerTest {

    private static final String CUSTOMER_NAME = "Customer Name";
    private static final String CUSTOMER_EMAIL = "customer.name@test.com";
    private static final CustomerId DEFAULT_ID = CustomerId.from("123e4567-e89b-42d3-a456-556642440000");

    @Mock
    private Customers customers;

    @InjectMocks
    private CreateCustomer createCustomer;

    @Test
    public void shouldBeActivateCustomerWithSuccess() {

        final Customer customer = new Customer(DEFAULT_ID, CUSTOMER_NAME, CUSTOMER_EMAIL, CustomerStatus.INACTIVE);

        when(customers.findByEmail(anyString())).thenReturn(Optional.of(customer));
        when(customers.createOrUpdate(any(Customer.class)))
            .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        final Customer createdCustomer = createCustomer.create(CUSTOMER_NAME, CUSTOMER_EMAIL);

        assertNotNull(createdCustomer);
        assertNotNull(createdCustomer.getId());
        assertEquals(DEFAULT_ID, createdCustomer.getId());
        assertEquals(CustomerStatus.ACTIVE, createdCustomer.getStatus());
    }

    @Test
    public void shouldThrowsCustomerEmailAlreadyExistsExceptionWhenANewCustomerIsCreatedWithAnExistingEmail() {
        final Customer customer = new Customer(DEFAULT_ID, CUSTOMER_NAME, CUSTOMER_EMAIL, CustomerStatus.ACTIVE);
        when(customers.findByEmail(anyString())).thenReturn(Optional.of(customer));
        assertThrows(CustomerEmailAlreadyExistsException.class, () -> createCustomer.create(CUSTOMER_NAME, CUSTOMER_EMAIL));
    }

    @Test
    public void shouldBeCreateCustomerWithSuccess() {

        when(customers.findByEmail(anyString())).thenReturn(Optional.empty());
        when(customers.createOrUpdate(any(Customer.class)))
            .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        final Customer createdCustomer = createCustomer.create(CUSTOMER_NAME, CUSTOMER_EMAIL);

        assertNotNull(createdCustomer);
        assertNotNull(createdCustomer.getId());
        assertNotEquals(DEFAULT_ID, createdCustomer.getId());
        assertEquals(CustomerStatus.ACTIVE, createdCustomer.getStatus());
    }
}
