package br.com.luizalabs.favoriteproducts.product.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.usecase.FindCustomer;
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

    private final FindCustomer findCustomer;

    @Inject
    public SearchProducts(final Products products, final FindCustomer findCustomer) {
        this.products = products;
        this.findCustomer = findCustomer;
    }

    public Set<Product> search(final CustomerId customerId, int pageNumber, int pageSize) {
        Customer customer = findCustomer.findOne(customerId);
        return products.search(customer.getId(), pageNumber, pageSize);
    }
}
