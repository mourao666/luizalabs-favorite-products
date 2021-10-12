package br.com.luizalabs.favoriteproducts.customer.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.CustomerStatus;
import br.com.luizalabs.favoriteproducts.customer.usecase.exception.CustomerEmailAlreadyExistsException;
import br.com.luizalabs.favoriteproducts.customer.usecase.port.Customers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateCustomerTest {

    private static final String CUSTOMER_NAME = "Customer Name";
    private static final String CUSTOMER_EMAIL = "customer.name@test.com";

    @Mock
    private Customers customers;

    @InjectMocks
    private CreateCustomer createCustomer;

    @Test
    public void shouldBeCreateCustomerWithSuccess() {

        when(customers.emailAlreadyExists(anyString()))
            .thenReturn(Boolean.FALSE);
        when(customers.createOrUpdate(any(Customer.class)))
            .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        final Customer customer = createCustomer.create(CUSTOMER_NAME, CUSTOMER_EMAIL);

        assertNotNull(customer);
        assertNotNull(customer.getId());
        assertEquals(CustomerStatus.ACTIVE, customer.getStatus());
    }

    @Test
    public void shouldThrowsCustomerEmailAlreadyExistsExceptionWhenANewCustomerIsCreatedWithAnExistingEmail() {
        when(customers.emailAlreadyExists(anyString())).thenReturn(Boolean.TRUE);
        assertThrows(CustomerEmailAlreadyExistsException.class, () -> createCustomer.create(CUSTOMER_NAME, CUSTOMER_EMAIL));
    }
}
