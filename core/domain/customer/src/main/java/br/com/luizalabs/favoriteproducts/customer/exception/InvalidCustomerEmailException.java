package br.com.luizalabs.favoriteproducts.customer.exception;

public class InvalidCustomerEmailException extends RuntimeException {

    public InvalidCustomerEmailException() {
        super("The customer email is invalid");
    }
}
