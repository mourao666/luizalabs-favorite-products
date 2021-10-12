package br.com.luizalabs.favoriteproducts.customer.usecase.port;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;

public interface Customers {

    Customer create(Customer customer);

    boolean emailAlreadyExists(String email);
}
