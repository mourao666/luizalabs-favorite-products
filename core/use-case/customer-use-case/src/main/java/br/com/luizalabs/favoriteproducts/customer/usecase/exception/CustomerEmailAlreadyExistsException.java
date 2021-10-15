package br.com.luizalabs.favoriteproducts.customer.usecase.exception;

import br.com.luizalabs.favoriteproducts.domain.exception.BusinessException;

public class CustomerEmailAlreadyExistsException extends BusinessException {

    public CustomerEmailAlreadyExistsException(final String email) {
        super("There is already an active customer registered with the email " + email);
    }
}
