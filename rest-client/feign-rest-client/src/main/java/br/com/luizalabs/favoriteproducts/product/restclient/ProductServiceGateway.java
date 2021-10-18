package br.com.luizalabs.favoriteproducts.product.restclient;

import br.com.luizalabs.favoriteproducts.product.domain.Product;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;
import br.com.luizalabs.favoriteproducts.product.restclient.client.ProductClient;
import br.com.luizalabs.favoriteproducts.product.restclient.dto.ProductResponse;
import br.com.luizalabs.favoriteproducts.product.restclient.mapper.ProductMapper;
import br.com.luizalabs.favoriteproducts.product.usecase.port.ProductsService;
import org.apache.commons.lang3.StringUtils;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
@ApplicationScoped
public class ProductServiceGateway implements ProductsService {

    private final ProductClient productClient;

    @Inject
    public ProductServiceGateway(final ProductClient productClient) {
        this.productClient = productClient;
    }

    @Override
    public Optional<Product> find(final ProductId id) {
        ProductResponse productResponse = productClient.getProduct(id.toString());
        return productResponse != null && StringUtils.isNotBlank(productResponse.getId())
            ? Optional.of(ProductMapper.toDomain(productResponse))
            : Optional.empty();
    }
}
