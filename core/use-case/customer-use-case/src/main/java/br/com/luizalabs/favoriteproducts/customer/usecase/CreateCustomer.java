package br.com.luizalabs.favoriteproducts.customer.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.CustomerStatus;
import br.com.luizalabs.favoriteproducts.customer.usecase.exception.CustomerEmailAlreadyExistsException;
import br.com.luizalabs.favoriteproducts.customer.usecase.port.Customers;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
@ApplicationScoped
public class CreateCustomer {

    private final Customers customers;

    @Inject
    public CreateCustomer(final Customers customers) {
        this.customers = customers;
    }

    public Customer create(final String name, final String email) {

        Optional<Customer> optionalCustomer = customers.findByEmail(email);

        if (optionalCustomer.isEmpty()) {

            final Customer customer = new Customer(name, email);
            return this.customers.createOrUpdate(customer);

        } else {

            Customer customer = optionalCustomer.get();
            if (CustomerStatus.ACTIVE.equals(customer.getStatus())) {
                throw new CustomerEmailAlreadyExistsException(email);
            } else {
                customer.updateName(name);
                customer.activate();
                return this.customers.createOrUpdate(customer);
            }
        }
    }
}
