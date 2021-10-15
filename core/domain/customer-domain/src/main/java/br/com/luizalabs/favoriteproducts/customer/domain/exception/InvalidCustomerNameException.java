package br.com.luizalabs.favoriteproducts.customer.domain.exception;

import br.com.luizalabs.favoriteproducts.domain.exception.BusinessException;

public class InvalidCustomerNameException extends BusinessException {

    public InvalidCustomerNameException(final String name) {
        super("The customer name " + name + " cannot be empty");
    }
}
