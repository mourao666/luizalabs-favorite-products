package br.com.luizalabs.favoriteproducts.product.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.product.domain.Product;
import br.com.luizalabs.favoriteproducts.product.usecase.port.Products;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchProductsTest {

    private static final CustomerId CUSTOMER_ID = CustomerId.from("123e4567-a456-42d3-e89b-556642440000");

    @Mock
    private Products products;

    @InjectMocks
    private SearchProducts searchProducts;

    @Test
    public void shouldBeSearchProductsWithSuccess() {
        when(products.search(any(CustomerId.class))).thenReturn(Collections.emptySet());
        Set<Product> productsSet = searchProducts.search(CUSTOMER_ID);
        assertNotNull(productsSet);
    }
}
