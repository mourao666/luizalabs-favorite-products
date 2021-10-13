package br.com.luizalabs.favoriteproducts.customer.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.usecase.exception.CustomerNotFoundException;
import br.com.luizalabs.favoriteproducts.customer.usecase.port.Customers;
import javax.inject.Inject;
import java.util.Objects;

public class DeleteCustomer {

    private final Customers customers;

    @Inject
    public DeleteCustomer(Customers customers) {
        this.customers = customers;
    }

    public void delete(CustomerId id) {

        Objects.requireNonNull(id, "Cannot delete a customer without id");

        if (customers.exists(id)) {
            customers.delete(id);
        } else {
            throw new CustomerNotFoundException(id);
        }
    }
}
