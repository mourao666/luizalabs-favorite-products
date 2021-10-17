package br.com.luizalabs.favoriteproducts.customer.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.CustomerStatus;
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
public class FindCustomer {

    private final Customers customers;

    @Inject
    public FindCustomer(final Customers customers) {
        this.customers = customers;
    }

    public Customer findOne(final CustomerId id) {
        Objects.requireNonNull(id, "Cannot search a customer without id");
        return customers.find(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public Customer findByEmail(String email) {

        Objects.requireNonNull(email, "Cannot search a customer without id");

        Optional<Customer> optionalCustomer = customers.findByEmail(email);
        if (optionalCustomer.isEmpty() || CustomerStatus.INACTIVE.equals(optionalCustomer.get().getStatus())) {
            throw new CustomerNotFoundException(email);
        } else {
            return optionalCustomer.get();
        }
    }
}
