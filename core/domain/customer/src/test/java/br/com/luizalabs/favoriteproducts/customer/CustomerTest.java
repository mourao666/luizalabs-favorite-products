package br.com.luizalabs.favoriteproducts.customer;

import br.com.luizalabs.favoriteproducts.customer.exception.InvalidCustomerNameException;
import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CustomerTest {

    private static final UUID DEFAULT_ID = UUID.fromString("123e4567-e89b-42d3-a456-556642440000");
    private static final String DEFAULT_NAME = "Customer Name";
    private static final String DEFAULT_EMAIL = "customer.name@mail.test";
    private static final String UPDATED_NAME = "New Customer Name";
    private static final String UPDATED_EMAIL = "new.customer.name@mail.test";

    private static final String EMPTY_STRING = "";
    private static final String BLANK_STRING = " ";

    // region new customer

    @Test
    public void shouldCreateANewCustomerSuccessfully() {

        Customer customer = new Customer(DEFAULT_NAME, DEFAULT_EMAIL);

        assertNotNull(customer.getId());
        assertEquals(DEFAULT_NAME, customer.getName());
        assertEquals(DEFAULT_EMAIL, customer.getEmail());
        assertEquals(CustomerStatus.ACTIVE, customer.getStatus());
    }

    @Test
    public void shouldThrowsInvalidCustomerNameExceptionWhenANewCustomerIsCreatedWithNullName() {
        assertThrows(InvalidCustomerNameException.class, () -> new Customer(null, DEFAULT_EMAIL));
    }

    @Test
    public void shouldThrowsInvalidCustomerNameExceptionWhenANewCustomerIsCreatedWithEmptyName() {
        assertThrows(InvalidCustomerNameException.class, () -> new Customer(EMPTY_STRING, DEFAULT_EMAIL));
    }

    @Test
    public void shouldThrowsInvalidCustomerNameExceptionWhenANewCustomerIsCreatedWithBlankName() {
        assertThrows(InvalidCustomerNameException.class, () -> new Customer(BLANK_STRING, DEFAULT_EMAIL));
    }

    // endregion

    // region existing customer

    @Test
    public void shouldCreateAnExistingCustomerSuccessfully() {

        Customer customer = new Customer(DEFAULT_ID, DEFAULT_NAME, DEFAULT_EMAIL, CustomerStatus.ACTIVE);

        assertEquals(DEFAULT_ID, customer.getId());
        assertEquals(DEFAULT_NAME, customer.getName());
        assertEquals(DEFAULT_EMAIL, customer.getEmail());
        assertEquals(CustomerStatus.ACTIVE, customer.getStatus());
    }

    @Test
    public void shouldThrowsInvalidCustomerNameExceptionWhenAnExistingCustomerIsCreatedWithNullName() {
        assertThrows(InvalidCustomerNameException.class, () -> new Customer(DEFAULT_ID, null, DEFAULT_EMAIL, CustomerStatus.ACTIVE));
    }

    @Test
    public void shouldThrowsInvalidCustomerNameExceptionWhenAnExistingCustomerIsCreatedWithEmptyName() {
        assertThrows(InvalidCustomerNameException.class, () -> new Customer(DEFAULT_ID, EMPTY_STRING, DEFAULT_EMAIL, CustomerStatus.ACTIVE));
    }

    @Test
    public void shouldThrowsInvalidCustomerNameExceptionWhenAnExistingCustomerIsCreatedWithBlankName() {
        assertThrows(InvalidCustomerNameException.class, () -> new Customer(DEFAULT_ID, BLANK_STRING, DEFAULT_EMAIL, CustomerStatus.ACTIVE));
    }

    // endregion

    // region update name

    @Test
    public void shouldUpdateACustomerNameSuccessfully() {

        Customer customer = new Customer(DEFAULT_NAME, DEFAULT_EMAIL);
        assertEquals(DEFAULT_NAME, customer.getName());

        customer.updateName(UPDATED_NAME);
        assertEquals(UPDATED_NAME, customer.getName());
    }

    @Test
    public void shouldThrowsInvalidCustomerNameExceptionWhenTryingToUpdateCustomerNameWithAnNullName() {
        Customer customer = new Customer(DEFAULT_NAME, DEFAULT_EMAIL);
        assertThrows(InvalidCustomerNameException.class, () -> customer.updateName(null));
    }

    @Test
    public void shouldThrowsInvalidCustomerNameExceptionWhenTryingToUpdateCustomerNameWithAnEmptyName() {
        Customer customer = new Customer(DEFAULT_NAME, DEFAULT_EMAIL);
        assertThrows(InvalidCustomerNameException.class, () -> customer.updateName(EMPTY_STRING));
    }

    @Test
    public void shouldThrowsInvalidCustomerNameExceptionWhenTryingToUpdateCustomerNameWithAnBlankName() {
        Customer customer = new Customer(DEFAULT_NAME, DEFAULT_EMAIL);
        assertThrows(InvalidCustomerNameException.class, () -> customer.updateName(BLANK_STRING));
    }

    // endregion

    // region update email

    @Test
    public void shouldUpdateACustomerEmailSuccessfully() {

        Customer customer = new Customer(DEFAULT_NAME, DEFAULT_EMAIL);
        assertEquals(DEFAULT_EMAIL, customer.getEmail());

        customer.updateEmail(UPDATED_EMAIL);
        assertEquals(UPDATED_EMAIL, customer.getEmail());
    }

    // endregion

    // region inactivate customer

    @Test
    public void shouldInactivateACustomerSuccessfully() {

        Customer customer = new Customer(DEFAULT_NAME, DEFAULT_EMAIL);
        assertEquals(CustomerStatus.ACTIVE, customer.getStatus());

        customer.inactivate();
        assertEquals(CustomerStatus.INACTIVE, customer.getStatus());
    }

    // endregion
}
