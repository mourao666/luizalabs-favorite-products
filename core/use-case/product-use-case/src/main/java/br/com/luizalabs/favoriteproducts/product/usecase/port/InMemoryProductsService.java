package br.com.luizalabs.favoriteproducts.product.usecase.port;

import br.com.luizalabs.favoriteproducts.product.domain.Product;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductPrice;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductReviewScore;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Named
@ApplicationScoped
public class InMemoryProductsService implements ProductsService {

    private static final Map<ProductId, Product> PRODUCTS = new HashMap<>() {
        {
            put(ProductId.from("b5116953-dbe5-7edc-c72e-377319e21d56"), new Product(ProductId.from("b5116953-dbe5-7edc-c72e-377319e21d56"), "Ico & Shadow Of The Colossus para PS3", "sony", ProductPrice.from(79.0d), "http://challenge-api.luizalabs.com/images/b5116953-dbe5-7edc-c72e-377319e21d56.jpg", ProductReviewScore.from(5.0d)));
            put(ProductId.from("673a179e-c348-e9b7-f88f-c7b338c638d3"), new Product(ProductId.from("673a179e-c348-e9b7-f88f-c7b338c638d3"), "Rel\u00f3gio Masculino Technos OS20IL/1P Anal\u00f3gico", "technos", ProductPrice.from(1034.0d), "http://challenge-api.luizalabs.com/images/673a179e-c348-e9b7-f88f-c7b338c638d3.jpg", ProductReviewScore.from(null)));
            put(ProductId.from("74c88794-0f33-38a2-0646-7a9f9d25010f"), new Product(ProductId.from("74c88794-0f33-38a2-0646-7a9f9d25010f"), "Tapete Carinha Le\u00e3o L\u00e9o", "anjos baby", ProductPrice.from(79.9d), "http://challenge-api.luizalabs.com/images/74c88794-0f33-38a2-0646-7a9f9d25010f.jpg", ProductReviewScore.from(null)));
            put(ProductId.from("27b4d7e3-23e9-f99c-d66e-44db54689c92"), new Product(ProductId.from("27b4d7e3-23e9-f99c-d66e-44db54689c92"), "Faqueiro Inox B\u00fazios 60 Pe\u00e7as", "tramontina", ProductPrice.from(119.0d), "http://challenge-api.luizalabs.com/images/27b4d7e3-23e9-f99c-d66e-44db54689c92.jpg", ProductReviewScore.from(null)));
            put(ProductId.from("f2eb3c48-9ef4-cc99-cdf7-d6175c58919b"), new Product(ProductId.from("f2eb3c48-9ef4-cc99-cdf7-d6175c58919b"), "Rel\u00f3gio MasculinoTechnos OS20IN/1P Anal\u00f3gico", "technos", ProductPrice.from(1089.0d), "http://challenge-api.luizalabs.com/images/f2eb3c48-9ef4-cc99-cdf7-d6175c58919b.jpg", ProductReviewScore.from(null)));
        }
    };

    @Override
    public Optional<Product> find(ProductId id) {
        return Optional.ofNullable(PRODUCTS.get(id));
    }
}
