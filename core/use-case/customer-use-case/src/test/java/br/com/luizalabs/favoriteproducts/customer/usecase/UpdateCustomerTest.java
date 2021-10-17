package br.com.luizalabs.favoriteproducts.customer.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.CustomerStatus;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.usecase.exception.CustomerEmailAlreadyExistsException;
import br.com.luizalabs.favoriteproducts.customer.usecase.exception.CustomerNotFoundException;
import br.com.luizalabs.favoriteproducts.customer.usecase.port.Customers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UpdateCustomerTest {

    private static final String DEFAULT_NAME = "Customer Name";
    private static final String DEFAULT_EMAIL = "customer.name@test.com";
    private static final String UPDATED_NAME = "New Customer Name";
    private static final String UPDATED_EMAIL = "new.customer.name@test.com";
    private static final CustomerId DEFAULT_ID = CustomerId.from("123e4567-e89b-42d3-a456-556642440000");

    @Mock
    private Customers customers;

    @InjectMocks
    private UpdateCustomer updateCustomer;

    // region update

    @Test
    public void shouldBeThrowsCustomerEmailAlreadyExistsExceptionWhenACustomerIsUpdatedWithExistingEmail() {
        final Customer customer = new Customer(DEFAULT_ID, DEFAULT_NAME, DEFAULT_EMAIL, CustomerStatus.ACTIVE);
        when(customers.find(any(CustomerId.class))).thenReturn(Optional.of(customer));
        when(customers.emailAlreadyExists(anyString())).thenReturn(Boolean.TRUE);
        assertThrows(CustomerEmailAlreadyExistsException.class, () -> updateCustomer.update(DEFAULT_ID, DEFAULT_NAME, UPDATED_EMAIL));
    }

    @Test
    public void shouldBeThrowsNullPointerExceptionWhenACustomerIsUpdatedWithNullId() {
        assertThrows(NullPointerException.class, () -> updateCustomer.update(null, DEFAULT_NAME, UPDATED_EMAIL));
    }

    @Test
    public void shouldBeThrowsCustomerNotFoundExceptionWhenACustomerIsUpdatedWithInvalidId() {
        when(customers.find(any(CustomerId.class))).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> updateCustomer.update(DEFAULT_ID, DEFAULT_NAME, UPDATED_EMAIL));
    }

    @Test
    public void shouldBeUpdateCustomerWithSuccess() {

        final Customer customer = new Customer(DEFAULT_ID, DEFAULT_NAME, DEFAULT_EMAIL, CustomerStatus.ACTIVE);

        when(customers.find(any(CustomerId.class))).thenReturn(Optional.of(customer));
        when(customers.emailAlreadyExists(anyString())).thenReturn(Boolean.FALSE);
        when(customers.createOrUpdate(any(Customer.class)))
            .then(invocationOnMock -> invocationOnMock.getArgument(0));

        final Customer updatedCustomer = updateCustomer.update(customer.getId(), UPDATED_NAME, UPDATED_EMAIL);
        assertNotNull(updatedCustomer);
        assertEquals(UPDATED_NAME, updatedCustomer.getName());
        assertEquals(UPDATED_EMAIL, updatedCustomer.getEmail());
    }

    // endregion

    // region update name

    @Test
    public void shouldBeUpdateCustomerNameWithSuccess() {

        final Customer customer = new Customer(DEFAULT_NAME, DEFAULT_EMAIL);

        when(customers.find(any(CustomerId.class))).thenReturn(Optional.of(customer));
        when(customers.createOrUpdate(any(Customer.class)))
            .then(invocationOnMock -> invocationOnMock.getArgument(0));

        final Customer updatedCustomer = updateCustomer.updateName(customer.getId(), UPDATED_NAME);
        assertNotNull(updatedCustomer);
        assertEquals(UPDATED_NAME, updatedCustomer.getName());
        assertEquals(DEFAULT_EMAIL, updatedCustomer.getEmail());
    }

    // endregion

    // region update email

    @Test
    public void shouldBeThrowsCustomerEmailAlreadyExistsExceptionWhenACustomerEmailIsUpdatedWithExistingEmail() {
        final Customer customer = new Customer(DEFAULT_ID, DEFAULT_NAME, DEFAULT_EMAIL, CustomerStatus.ACTIVE);
        when(customers.find(any(CustomerId.class))).thenReturn(Optional.of(customer));
        when(customers.emailAlreadyExists(anyString())).thenReturn(Boolean.TRUE);
        assertThrows(CustomerEmailAlreadyExistsException.class, () -> updateCustomer.updateEmail(DEFAULT_ID, UPDATED_EMAIL));
    }

    @Test
    public void shouldBeUpdateCustomerEmailWithSuccess() {

        final Customer customer = new Customer(DEFAULT_NAME, DEFAULT_EMAIL);

        when(customers.emailAlreadyExists(anyString())).thenReturn(Boolean.FALSE);
        when(customers.find(any(CustomerId.class))).thenReturn(Optional.of(customer));
        when(customers.createOrUpdate(any(Customer.class)))
            .then(invocationOnMock -> invocationOnMock.getArgument(0));

        final Customer updatedCustomer = updateCustomer.updateEmail(customer.getId(), UPDATED_EMAIL);
        assertNotNull(updatedCustomer);
        assertEquals(DEFAULT_NAME, updatedCustomer.getName());
        assertEquals(UPDATED_EMAIL, updatedCustomer.getEmail());
    }

    // endregion
}
