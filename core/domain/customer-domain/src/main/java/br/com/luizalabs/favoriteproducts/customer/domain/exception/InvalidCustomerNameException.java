package br.com.luizalabs.favoriteproducts.customer.domain.exception;

public class InvalidCustomerNameException extends RuntimeException {

    public InvalidCustomerNameException(final String name) {
        super("The customer name " + name + " cannot be empty");
    }
}
