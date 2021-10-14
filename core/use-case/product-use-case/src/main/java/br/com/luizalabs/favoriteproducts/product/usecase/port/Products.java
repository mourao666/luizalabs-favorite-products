package br.com.luizalabs.favoriteproducts.product.usecase.port;

import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.product.domain.Product;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;

public interface Products {

    void add(Product product, CustomerId customerId);

    boolean alreadyBeenAdded(ProductId productId, CustomerId customerId);
}
