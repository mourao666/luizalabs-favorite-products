package br.com.luizalabs.favoriteproducts.product.domain;

import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductPrice;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductReviewScore;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProductTest {

    @Test
    public void shouldCreateANewProductSuccessfully() {

        ProductId id = ProductId.from("123e4567-e89b-42d3-a456-556642440000");
        ProductPrice price = ProductPrice.from(BigDecimal.TEN);

        final Product product = new Product(id, "Dummy product", "dummy", price, "xpto.png", ProductReviewScore.from(4.5d));
        assertNotNull(product);
        assertNotNull(product.getId());
        assertEquals(id.toString(), product.getId().toString());
        assertEquals("Dummy product", product.getTitle());
        assertEquals("dummy", product.getBrand());
        assertEquals("xpto.png", product.getImage());
        assertEquals(4.5d, product.getReviewScore().value());

        Product productWithoutReview = new Product(id, "Dummy product", "dummy", price, "xpto.png", ProductReviewScore.from(null));
        assertNotNull(product);
        assertNotNull(product.getReviewScore());
    }
}
