package br.com.luizalabs.favoriteproducts.product.usecase;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.CustomerStatus;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.usecase.FindCustomer;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;
import br.com.luizalabs.favoriteproducts.product.usecase.exception.ProductHasNotBeenAddedException;
import br.com.luizalabs.favoriteproducts.product.usecase.port.Products;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RemoveProductTest {

    private static final ProductId PRODUCT_ID = ProductId.from("123e4567-e89b-42d3-a456-556642440000");
    private static final CustomerId CUSTOMER_ID = CustomerId.from("123e4567-a456-42d3-e89b-556642440000");

    private static final Customer CUSTOMER = new Customer(CUSTOMER_ID, "Dummy", "dummy@testmail.com", CustomerStatus.ACTIVE);

    @Mock
    private Products products;

    @Mock
    private FindCustomer findCustomer;

    @InjectMocks
    private RemoveProduct removeProduct;

    @Test
    public void shouldBeRemoveProductWithSuccess() {
        when(findCustomer.findOne(any(CustomerId.class))).thenReturn(CUSTOMER);
        when(products.alreadyBeenAdded(any(ProductId.class), any(CustomerId.class))).thenReturn(Boolean.TRUE);
        removeProduct.remove(PRODUCT_ID, CUSTOMER_ID);
    }

    @Test
    public void shouldThrowsProductHasNotBeenAddedExceptionWhenAProductIsRemovedWithInvalidId() {
        when(findCustomer.findOne(any(CustomerId.class))).thenReturn(CUSTOMER);
        when(products.alreadyBeenAdded(any(ProductId.class), any(CustomerId.class))).thenReturn(Boolean.FALSE);
        assertThrows(ProductHasNotBeenAddedException.class, () -> removeProduct.remove(PRODUCT_ID, CUSTOMER_ID));
    }
}
