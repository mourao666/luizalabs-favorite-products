package br.com.luizalabs.favoriteproducts.customer.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.usecase.exception.CustomerNotFoundException;
import br.com.luizalabs.favoriteproducts.customer.usecase.port.Customers;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Objects;

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

        if (customers.exists(id)) {
            customers.delete(id);
        } else {
            throw new CustomerNotFoundException(id);
        }
    }
}
