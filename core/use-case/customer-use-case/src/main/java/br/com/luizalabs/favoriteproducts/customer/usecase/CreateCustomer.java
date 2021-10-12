package br.com.luizalabs.favoriteproducts.customer.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.usecase.exception.CustomerEmailAlreadyExistsException;
import br.com.luizalabs.favoriteproducts.customer.usecase.port.Customers;
import javax.inject.Inject;

public class CreateCustomer {

    private final Customers customers;

    @Inject
    public CreateCustomer(final Customers customers) {
        this.customers = customers;
    }

    public Customer create(final String name, final String email) {

        if (customers.emailAlreadyExists(email)) {
            throw new CustomerEmailAlreadyExistsException(email);
        }

        final Customer customer = new Customer(name, email);
        return this.customers.createOrUpdate(customer);
    }
}
