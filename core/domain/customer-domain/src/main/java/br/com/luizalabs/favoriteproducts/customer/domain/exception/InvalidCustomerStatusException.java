package br.com.luizalabs.favoriteproducts.customer.domain.exception;

public class InvalidCustomerStatusException extends RuntimeException {

    public InvalidCustomerStatusException() {
        super("The current status of the customer does not allow the operation");
    }
}
