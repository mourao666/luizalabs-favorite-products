package br.com.luizalabs.favoriteproducts.customer.exception;

public class InvalidCustomerNameException extends RuntimeException {

    public InvalidCustomerNameException() {
        super("The customer name cannot be empty");
    }
}
