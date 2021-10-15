package br.com.luizalabs.favoriteproducts.customer.domain.exception;

public class InvalidCustomerEmailException extends RuntimeException {

    public InvalidCustomerEmailException(final String email) {
        super("The customer email " + email + " is invalid");
    }
}
