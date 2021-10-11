package br.com.luizalabs.favoriteproducts.customer;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerTest {

    private static final UUID DEFAULT_ID = UUID.fromString("123e4567-e89b-42d3-a456-556642440000");
    private static final String DEFAULT_NAME = "Customer Name";
    private static final String DEFAULT_EMAIL = "customer.name@mail.test";
    private static final String UPDATED_NAME = "New Customer Name";
    private static final String UPDATED_EMAIL = "new.customer.name@mail.test";

    @Test
    public void shouldCreateANewCustomerSuccessfully() {

        Customer customer = new Customer(DEFAULT_NAME, DEFAULT_EMAIL);

        assertNotNull(customer.getId());
        assertEquals(DEFAULT_NAME, customer.getName());
        assertEquals(DEFAULT_EMAIL, customer.getEmail());
        assertEquals(CustomerStatus.ACTIVE, customer.getStatus());
    }

    @Test
    public void shouldCreateAnExistingCustomerSuccessfully() {

        Customer customer = new Customer(DEFAULT_ID, DEFAULT_NAME, DEFAULT_EMAIL, CustomerStatus.ACTIVE);

        assertEquals(DEFAULT_ID, customer.getId());
        assertEquals(DEFAULT_NAME, customer.getName());
        assertEquals(DEFAULT_EMAIL, customer.getEmail());
        assertEquals(CustomerStatus.ACTIVE, customer.getStatus());
    }

    @Test
    public void shouldUpdateACustomerNameSuccessfully() {

        Customer customer = new Customer(DEFAULT_NAME, DEFAULT_EMAIL);
        assertEquals(DEFAULT_NAME, customer.getName());

        customer.updateName(UPDATED_NAME);
        assertEquals(UPDATED_NAME, customer.getName());
    }

    @Test
    public void shouldUpdateACustomerEmailSuccessfully() {

        Customer customer = new Customer(DEFAULT_NAME, DEFAULT_EMAIL);
        assertEquals(DEFAULT_EMAIL, customer.getEmail());

        customer.updateEmail(UPDATED_EMAIL);
        assertEquals(UPDATED_EMAIL, customer.getEmail());
    }

    @Test
    public void shouldInactivateACustomerSuccessfully() {

        Customer customer = new Customer(DEFAULT_NAME, DEFAULT_EMAIL);
        assertEquals(CustomerStatus.ACTIVE, customer.getStatus());

        customer.inactivate();
        assertEquals(CustomerStatus.INACTIVE, customer.getStatus());
    }
}
