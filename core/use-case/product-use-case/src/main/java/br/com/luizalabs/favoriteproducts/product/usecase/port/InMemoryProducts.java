package br.com.luizalabs.favoriteproducts.product.usecase.port;

import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.product.domain.Product;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//@Named
//@ApplicationScoped
public class InMemoryProducts implements Products {

    private static final Map<CustomerId, Map<ProductId, Product>> CUSTOMER_PRODUCTS = new HashMap<>();

    @Override
    public void add(Product product, CustomerId customerId) {

        if (!CUSTOMER_PRODUCTS.containsKey(customerId)) {
            CUSTOMER_PRODUCTS.put(customerId, new HashMap<>());
        }

        if (!CUSTOMER_PRODUCTS.get(customerId).containsKey(product.getId())) {
            CUSTOMER_PRODUCTS.get(customerId).put(product.getId(), product);
        }
    }

    @Override
    public void remove(ProductId productId, CustomerId customerId) {
        CUSTOMER_PRODUCTS.get(customerId).remove(productId);
    }

    @Override
    public boolean alreadyBeenAdded(ProductId productId, CustomerId customerId) {
        return CUSTOMER_PRODUCTS.containsKey(customerId) && CUSTOMER_PRODUCTS.get(customerId).containsKey(productId);
    }

    @Override
    public Set<Product> search(CustomerId customerId) {
        return new HashSet<>(CUSTOMER_PRODUCTS.get(customerId).values());
    }
}
