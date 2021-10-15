package br.com.luizalabs.favoriteproducts.product.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.CustomerStatus;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.usecase.FindCustomer;
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

    private static final Customer CUSTOMER = new Customer(CUSTOMER_ID, "Dummy", "dummy@testmail.com", CustomerStatus.ACTIVE);

    @Mock
    private Products products;

    @Mock
    private FindCustomer findCustomer;

    @InjectMocks
    private SearchProducts searchProducts;

    @Test
    public void shouldBeSearchProductsWithSuccess() {
        when(findCustomer.findOne(any(CustomerId.class))).thenReturn(CUSTOMER);
        when(products.search(any(CustomerId.class))).thenReturn(Collections.emptySet());
        Set<Product> productsSet = searchProducts.search(CUSTOMER_ID);
        assertNotNull(productsSet);
    }
}
