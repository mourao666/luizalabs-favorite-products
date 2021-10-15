package br.com.luizalabs.favoriteproducts.product.usecase.exception;

import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.domain.exception.BusinessException;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;

public class ProductHasNotBeenAddedException extends BusinessException {

    public ProductHasNotBeenAddedException(final ProductId productId, final CustomerId customerId) {
        super("Product with id " + productId + " has not been added in customer " + customerId + " list");
    }
}
