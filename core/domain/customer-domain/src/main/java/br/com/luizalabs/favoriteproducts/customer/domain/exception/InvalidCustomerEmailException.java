package br.com.luizalabs.favoriteproducts.customer.domain.exception;

import br.com.luizalabs.favoriteproducts.domain.exception.BusinessException;

public class InvalidCustomerEmailException extends BusinessException {

    public InvalidCustomerEmailException(final String email) {
        super("The customer email " + email + " is invalid");
    }
}
