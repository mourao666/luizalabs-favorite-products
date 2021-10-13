package br.com.luizalabs.favoriteproducts.customer.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.usecase.exception.CustomerNotFoundException;
import br.com.luizalabs.favoriteproducts.customer.usecase.port.Customers;
import javax.inject.Inject;
import java.util.Objects;

public class FindCustomer {

    private final Customers customers;

    @Inject
    public FindCustomer(Customers customers) {
        this.customers = customers;
    }

    public Customer findOne(final CustomerId id) {
        Objects.requireNonNull(id, "Cannot search a customer without id");
        return customers.find(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }
}
