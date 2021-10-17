package br.com.luizalabs.favoriteproducts.customer.usecase.port;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.CustomerStatus;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

//@Named
//@ApplicationScoped
public class InMemoryCustomers implements Customers {

    private static final Map<CustomerId, Customer> CUSTOMERS = new HashMap<>();
    private static final Set<String> CUSTOMERS_EMAILS = new HashSet<>();

    @Override
    public Customer createOrUpdate(Customer customer) {

        if (CUSTOMERS.containsKey(customer.getId())) {
            Customer c = CUSTOMERS.remove(customer.getId());
            CUSTOMERS_EMAILS.remove(c.getEmail());
        }

        if (CustomerStatus.ACTIVE.equals(customer.getStatus())) {
            CUSTOMERS.put(customer.getId(), customer);
            CUSTOMERS_EMAILS.add(customer.getEmail());
        }

        return customer;
    }

    @Override
    public Optional<Customer> find(CustomerId id) {
        return Optional.ofNullable(CUSTOMERS.get(id));
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return CUSTOMERS.values().stream().filter(c -> c.getEmail().equals(email)).findFirst();
    }

    @Override
    public boolean emailAlreadyExists(String email) {
        return CUSTOMERS_EMAILS.contains(email);
    }
}
