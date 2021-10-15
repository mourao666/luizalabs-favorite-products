package br.com.luizalabs.favoriteproducts.product.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.usecase.FindCustomer;
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

    private final FindCustomer findCustomer;

    @Inject
    public RemoveProduct(final Products products, final FindCustomer findCustomer) {
        this.products = products;
        this.findCustomer = findCustomer;
    }

    public void remove(final ProductId productId, final CustomerId customerId) {

        Customer customer = findCustomer.findOne(customerId);

        if (products.alreadyBeenAdded(productId, customer.getId())) {
            products.remove(productId, customer.getId());
        } else {
            throw new ProductHasNotBeenAddedException(productId, customer.getId());
        }
    }
}
