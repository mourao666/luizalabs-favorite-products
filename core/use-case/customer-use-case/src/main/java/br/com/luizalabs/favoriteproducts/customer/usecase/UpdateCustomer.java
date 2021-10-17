package br.com.luizalabs.favoriteproducts.customer.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.usecase.exception.CustomerEmailAlreadyExistsException;
import br.com.luizalabs.favoriteproducts.customer.usecase.exception.CustomerNotFoundException;
import br.com.luizalabs.favoriteproducts.customer.usecase.port.Customers;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;
import java.util.Optional;

@Named
@ApplicationScoped
public class UpdateCustomer {

    private final Customers customers;

    @Inject
    public UpdateCustomer(final Customers customers) {
        this.customers = customers;
    }

    public Customer update(final CustomerId id, final String name, final String email) {

        final Customer customer = this.findCustomer(id);
        this.updateName(customer, name);
        this.updateEmail(customer, email);

        return customers.createOrUpdate(customer);
    }

    private Customer findCustomer(final CustomerId id) {

        Objects.requireNonNull(id, "Cannot update a customer without id");

        final Optional<Customer> optionalCustomer = customers.find(id);
        if (optionalCustomer.isEmpty()) {
            throw new CustomerNotFoundException(id);
        }

        return optionalCustomer.get();
    }

    public Customer updateName(final CustomerId id, final String name) {

        final Customer customer = this.findCustomer(id);
        this.updateName(customer, name);

        return customers.createOrUpdate(customer);
    }

    private void updateName(final Customer customer, final String name) {
        if (!customer.getName().equals(name)) {
            customer.updateName(name);
        }
    }

    public Customer updateEmail(final CustomerId id, final String email) {

        final Customer customer = this.findCustomer(id);
        this.updateEmail(customer, email);

        return customers.createOrUpdate(customer);
    }

    private void updateEmail(final Customer customer, final String email) {

        if (!customer.getEmail().equals(email)) {

            if (customers.emailAlreadyExists(email)) {
                throw new CustomerEmailAlreadyExistsException(email);
            }

            customer.updateEmail(email);
        }
    }
}
