package br.com.luizalabs.favoriteproducts.product.usecase.exception;

import br.com.luizalabs.favoriteproducts.domain.exception.BusinessException;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;

public class ProductNotFoundException extends BusinessException {

    public ProductNotFoundException(final ProductId id) {
        super("Product with Id " + id + " not found");
    }
}
