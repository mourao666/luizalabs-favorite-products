package br.com.luizalabs.favoriteproducts.customer.usecase.exception;

import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(final CustomerId id) {
        super("Customer with Id " + id + " not found");
    }
}
