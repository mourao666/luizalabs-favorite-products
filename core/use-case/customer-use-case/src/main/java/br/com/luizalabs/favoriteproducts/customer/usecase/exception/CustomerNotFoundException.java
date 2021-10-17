package br.com.luizalabs.favoriteproducts.customer.usecase.exception;

import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.domain.exception.BusinessException;

public class CustomerNotFoundException extends BusinessException {

    public CustomerNotFoundException(final CustomerId id) {
        super("Customer with Id " + id + " not found");
    }

    public CustomerNotFoundException(final String email) {
        super("Customer with email " + email + " not found");
    }
}
