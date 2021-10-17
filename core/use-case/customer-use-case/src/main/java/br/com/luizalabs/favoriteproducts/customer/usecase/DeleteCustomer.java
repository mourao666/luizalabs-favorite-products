package br.com.luizalabs.favoriteproducts.customer.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.usecase.exception.CustomerNotFoundException;
import br.com.luizalabs.favoriteproducts.customer.usecase.port.Customers;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;
import java.util.Optional;

@Named
@ApplicationScoped
public class DeleteCustomer {

    private final Customers customers;

    @Inject
    public DeleteCustomer(final Customers customers) {
        this.customers = customers;
    }

    public void delete(final CustomerId id) {

        Objects.requireNonNull(id, "Cannot delete a customer without id");

        final Optional<Customer> optionalCustomer = customers.find(id);
        if (optionalCustomer.isPresent()) {

            final Customer customer = optionalCustomer.get();
            customer.inactivate();
            customers.createOrUpdate(customer);

        } else {
            throw new CustomerNotFoundException(id);
        }
    }
}
