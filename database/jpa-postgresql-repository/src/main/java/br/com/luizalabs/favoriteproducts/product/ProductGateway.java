package br.com.luizalabs.favoriteproducts.product;

import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.entity.CustomerEntity;
import br.com.luizalabs.favoriteproducts.customer.repository.CustomerEntityRepository;
import br.com.luizalabs.favoriteproducts.product.domain.Product;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;
import br.com.luizalabs.favoriteproducts.product.entity.ProductEntity;
import br.com.luizalabs.favoriteproducts.product.entity.ProductEntityId;
import br.com.luizalabs.favoriteproducts.product.mapper.ProductMapper;
import br.com.luizalabs.favoriteproducts.product.repository.ProductEntityRepository;
import br.com.luizalabs.favoriteproducts.product.usecase.port.Products;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.Set;

@Named
@ApplicationScoped
public class ProductGateway implements Products {

    private final ProductEntityRepository productRepository;

    private final CustomerEntityRepository customerRepository;

    @Inject
    public ProductGateway(ProductEntityRepository productRepository, CustomerEntityRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void add(Product product, CustomerId customerId) {

        CustomerEntity customerEntity = customerRepository.getById(customerId.value());

        ProductEntity productEntity = ProductMapper.toEntity(product, customerId);
        productEntity.setCustomer(customerEntity);

        productRepository.save(productEntity);
    }

    @Override
    public void remove(ProductId productId, CustomerId customerId) {
        productRepository.deleteById(new ProductEntityId(productId.value(), customerId.value()));
    }

    @Override
    public boolean alreadyBeenAdded(ProductId productId, CustomerId customerId) {
        return productRepository.findById(new ProductEntityId(productId.value(), customerId.value())).isPresent();
    }

    @Override
    @Transactional
    public Set<Product> search(CustomerId customerId) {
        CustomerEntity customerEntity = customerRepository.getById(customerId.value());
        Set<ProductEntity> productEntities = customerEntity.getProducts();
        return ProductMapper.parse(productEntities);
    }
}
