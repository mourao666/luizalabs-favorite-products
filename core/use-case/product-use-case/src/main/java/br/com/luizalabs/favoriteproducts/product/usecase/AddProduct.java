package br.com.luizalabs.favoriteproducts.product.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.usecase.FindCustomer;
import br.com.luizalabs.favoriteproducts.product.domain.Product;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;
import br.com.luizalabs.favoriteproducts.product.usecase.exception.ProductAlreadyAddedException;
import br.com.luizalabs.favoriteproducts.product.usecase.exception.ProductNotFoundException;
import br.com.luizalabs.favoriteproducts.product.usecase.port.Products;
import br.com.luizalabs.favoriteproducts.product.usecase.port.ProductsService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
@ApplicationScoped
public class AddProduct {

    private final Products products;
    private final ProductsService productsService;

    private final FindCustomer findCustomer;

    @Inject
    public AddProduct(final Products products, final ProductsService productsService, final FindCustomer findCustomer) {
        this.products = products;
        this.productsService = productsService;
        this.findCustomer = findCustomer;
    }

    public Product add(final ProductId productId, final CustomerId customerId) {

        Customer customer = findCustomer.findOne(customerId);

        if (products.alreadyBeenAdded(productId, customer.getId())) {
            throw new ProductAlreadyAddedException(productId, customerId);
        }

        Optional<Product> optionalProduct = productsService.find(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }

        Product product = optionalProduct.get();
        products.add(product, customerId);
        return product;
    }
}
