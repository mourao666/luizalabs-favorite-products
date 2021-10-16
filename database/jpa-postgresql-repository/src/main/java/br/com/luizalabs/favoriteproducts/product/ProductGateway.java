package br.com.luizalabs.favoriteproducts.product;

import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.product.domain.Product;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;
import br.com.luizalabs.favoriteproducts.product.repository.ProductEntityRepository;
import br.com.luizalabs.favoriteproducts.product.usecase.port.Products;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Set;

@Named
@ApplicationScoped
public class ProductGateway implements Products {

    private final ProductEntityRepository repository;

    @Inject
    public ProductGateway(ProductEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void add(Product product, CustomerId customerId) {

    }

    @Override
    public void remove(ProductId productId, CustomerId customerId) {

    }

    @Override
    public boolean alreadyBeenAdded(ProductId productId, CustomerId customerId) {
        return false;
    }

    @Override
    public Set<Product> search(CustomerId customerId) {
        return null;
    }
}
