package br.com.luizalabs.favoriteproducts.product.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.CustomerStatus;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.usecase.FindCustomer;
import br.com.luizalabs.favoriteproducts.product.domain.Product;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductPrice;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductReviewScore;
import br.com.luizalabs.favoriteproducts.product.usecase.exception.ProductAlreadyAddedException;
import br.com.luizalabs.favoriteproducts.product.usecase.exception.ProductNotFoundException;
import br.com.luizalabs.favoriteproducts.product.usecase.port.Products;
import br.com.luizalabs.favoriteproducts.product.usecase.port.ProductsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddProductTest {

    private static final ProductId PRODUCT_ID = ProductId.from("123e4567-e89b-42d3-a456-556642440000");
    private static final CustomerId CUSTOMER_ID = CustomerId.from("123e4567-a456-42d3-e89b-556642440000");

    private static final Customer CUSTOMER = new Customer(CUSTOMER_ID, "Dummy", "dummy@testmail.com", CustomerStatus.ACTIVE);

    @Mock
    private Products products;

    @Mock
    private ProductsService productsService;

    @Mock
    private FindCustomer findCustomer;

    @InjectMocks
    private AddProduct addProduct;

    @Test
    public void shouldBeAddProductWithSuccess() {

        final Product product = new Product(
            PRODUCT_ID, "Dummy product", "dummy", ProductPrice.from(BigDecimal.TEN), "xpto.png", ProductReviewScore.from(4.5d));

        when(findCustomer.findOne(any(CustomerId.class))).thenReturn(CUSTOMER);
        when(products.alreadyBeenAdded(any(ProductId.class), any(CustomerId.class))).thenReturn(Boolean.FALSE);
        when(productsService.find(any(ProductId.class))).thenReturn(Optional.of(product));

        final Product addedProduct = addProduct.add(PRODUCT_ID, CUSTOMER_ID);
        assertNotNull(addedProduct);
        assertEquals(PRODUCT_ID, addedProduct.getId());
    }

    @Test
    public void shouldThrowsProductNotFoundExceptionWhenAProductIsAddedWithInvalidId() {
        when(findCustomer.findOne(any(CustomerId.class))).thenReturn(CUSTOMER);
        when(products.alreadyBeenAdded(any(ProductId.class), any(CustomerId.class))).thenReturn(Boolean.FALSE);
        when(productsService.find(any(ProductId.class))).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> addProduct.add(PRODUCT_ID, CUSTOMER_ID));
    }

    @Test
    public void shouldThrowsProductAlreadyAddedExceptionWhenAExistingProductIsAdded() {
        when(findCustomer.findOne(any(CustomerId.class))).thenReturn(CUSTOMER);
        when(products.alreadyBeenAdded(any(ProductId.class), any(CustomerId.class))).thenReturn(Boolean.TRUE);
        assertThrows(ProductAlreadyAddedException.class, () -> addProduct.add(PRODUCT_ID, CUSTOMER_ID));
    }
}
