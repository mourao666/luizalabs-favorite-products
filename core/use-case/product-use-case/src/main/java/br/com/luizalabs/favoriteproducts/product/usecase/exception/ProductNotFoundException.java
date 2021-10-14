package br.com.luizalabs.favoriteproducts.product.usecase.exception;

import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(ProductId id) {
        super("Product with Id " + id + " not found");
    }
}
