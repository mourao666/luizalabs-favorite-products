package br.com.luizalabs.favoriteproducts.product.usecase.port;

import br.com.luizalabs.favoriteproducts.product.domain.Product;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;
import java.util.Optional;

public interface ProductsService {

    Optional<Product> find(ProductId id);
}
