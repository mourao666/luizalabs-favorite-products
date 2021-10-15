package br.com.luizalabs.favoriteproducts.customer.domain.exception;

import br.com.luizalabs.favoriteproducts.customer.domain.CustomerStatus;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;

public class InvalidCustomerStatusException extends RuntimeException {

    public InvalidCustomerStatusException(final CustomerId id, final CustomerStatus status) {
        super("The current status " + status + " of the customer " + id + " does not allow the operation");
    }
}
