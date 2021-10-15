package br.com.luizalabs.favoriteproducts.product.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.product.domain.Product;
import br.com.luizalabs.favoriteproducts.product.usecase.port.Products;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Set;

@Named
@ApplicationScoped
public class SearchProducts {

    private final Products products;

    @Inject
    public SearchProducts(final Products products) {
        this.products = products;
    }

    public Set<Product> search(final CustomerId customerId) {
        return products.search(customerId);
    }
}
