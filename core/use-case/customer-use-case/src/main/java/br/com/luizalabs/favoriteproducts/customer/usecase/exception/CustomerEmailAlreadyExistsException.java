package br.com.luizalabs.favoriteproducts.customer.usecase.exception;

public class CustomerEmailAlreadyExistsException extends RuntimeException {

    public CustomerEmailAlreadyExistsException(final String email) {
        super("There is already an active customer registered with the email " + email);
    }
}
