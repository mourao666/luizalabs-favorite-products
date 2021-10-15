package br.com.luizalabs.favoriteproducts.product.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;
import br.com.luizalabs.favoriteproducts.product.usecase.exception.ProductHasNotBeenAddedException;
import br.com.luizalabs.favoriteproducts.product.usecase.port.Products;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class RemoveProduct {

    private final Products products;

    @Inject
    public RemoveProduct(final Products products) {
        this.products = products;
    }

    public void remove(final ProductId productId, final CustomerId customerId) {

        if (products.alreadyBeenAdded(productId, customerId)) {
            products.remove(productId, customerId);
        } else {
            throw new ProductHasNotBeenAddedException(productId, customerId);
        }
    }
}
