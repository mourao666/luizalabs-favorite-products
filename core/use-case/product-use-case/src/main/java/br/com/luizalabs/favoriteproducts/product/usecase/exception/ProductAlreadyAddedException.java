package br.com.luizalabs.favoriteproducts.product.usecase.exception;

import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;

public class ProductAlreadyAddedException extends RuntimeException {

    public ProductAlreadyAddedException(ProductId productId, CustomerId customerId) {
        super("Product with id " + productId + " is already added in customer " + customerId + " list");
    }
}
