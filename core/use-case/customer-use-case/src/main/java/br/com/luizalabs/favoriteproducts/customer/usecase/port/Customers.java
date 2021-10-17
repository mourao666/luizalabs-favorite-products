package br.com.luizalabs.favoriteproducts.customer.usecase.port;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import java.util.Optional;

public interface Customers {

    Customer createOrUpdate(Customer customer);

    Optional<Customer> find(CustomerId id);

    Optional<Customer> findByEmail(String email);

    boolean emailAlreadyExists(String email);
}
