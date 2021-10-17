package br.com.luizalabs.favoriteproducts.product;

import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.entity.CustomerEntity;
import br.com.luizalabs.favoriteproducts.customer.entity.CustomerEntityStatus;
import br.com.luizalabs.favoriteproducts.customer.repository.CustomerEntityRepository;
import br.com.luizalabs.favoriteproducts.product.domain.Product;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductPrice;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductReviewScore;
import br.com.luizalabs.favoriteproducts.product.entity.ProductEntity;
import br.com.luizalabs.favoriteproducts.product.entity.ProductEntityId;
import br.com.luizalabs.favoriteproducts.product.repository.ProductEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductGatewayTest {

    private static final String CUSTOMER_STRING_ID = "123e4567-a456-42d3-e89b-556642440000";
    private static final String PRODUCT_STRING_ID = "123e4567-42d3-a456-e89b-556642440000";

    private static final UUID CUSTOMER_UUID = UUID.fromString(CUSTOMER_STRING_ID);
    private static final UUID PRODUCT_UUID = UUID.fromString(PRODUCT_STRING_ID);
    private static final ProductEntityId PRODUCT_ENTITY_ID = new ProductEntityId(PRODUCT_UUID, CUSTOMER_UUID);

    private static final CustomerId CUSTOMER_ID = CustomerId.from(CUSTOMER_STRING_ID);
    private static final ProductId PRODUCT_ID = ProductId.from(PRODUCT_STRING_ID);

    private static final CustomerEntity CUSTOMER_ENTITY = new CustomerEntity(CUSTOMER_ID.value(), "Dummy", "dummy@testmail.com", CustomerEntityStatus.ACTIVE, null);

    private static final Product PRODUCT = new Product(PRODUCT_ID, "Ico & Shadow Of The Colossus para PS3", "sony", ProductPrice.from(79.0d), "http://challenge-api.luizalabs.com/images/b5116953-dbe5-7edc-c72e-377319e21d56.jpg", ProductReviewScore.from(5.0d));
    private static final ProductEntity PRODUCT_ENTITY = new ProductEntity(PRODUCT_ENTITY_ID, CUSTOMER_ENTITY, "Ico & Shadow Of The Colossus para PS3", "sony", BigDecimal.valueOf(79.0d), "http://challenge-api.luizalabs.com/images/b5116953-dbe5-7edc-c72e-377319e21d56.jpg", 5.0d);

    static {
        CUSTOMER_ENTITY.setProducts(Set.of(PRODUCT_ENTITY));
    }

    @Mock
    private CustomerEntityRepository customerRepository;

    @Mock
    private ProductEntityRepository productRepository;

    @InjectMocks
    private ProductGateway productGateway;

    @Test
    public void whenAddThenCallRepository() {

        when(customerRepository.getById(any(UUID.class))).thenReturn(CUSTOMER_ENTITY);
        when(productRepository.save(any(ProductEntity.class))).thenReturn(PRODUCT_ENTITY);

        productGateway.add(PRODUCT, CUSTOMER_ID);

        ArgumentCaptor<ProductEntity> captor = ArgumentCaptor.forClass(ProductEntity.class);
        verify(productRepository).save(captor.capture());
        verifyNoMoreInteractions(productRepository);

        ProductEntity productEntity = captor.getValue();
        assertNotNull(productEntity);
        assertEquals(PRODUCT.getId().value(), productEntity.getId().getProductId());
    }

    @Test
    public void whenRemoveThenCallRepository() {
        productGateway.remove(PRODUCT_ID, CUSTOMER_ID);
        verify(productRepository).deleteById(PRODUCT_ENTITY_ID);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    public void whenAlreadyBeenAddedThenCallRepository() {

        when(productRepository.findById(PRODUCT_ENTITY_ID)).thenReturn(Optional.of(PRODUCT_ENTITY));
        boolean result = productGateway.alreadyBeenAdded(PRODUCT_ID, CUSTOMER_ID);

        assertTrue(result);

        verify(productRepository).findById(PRODUCT_ENTITY_ID);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    public void whenNotBeenAddedThenCallRepository() {

        when(productRepository.findById(PRODUCT_ENTITY_ID)).thenReturn(Optional.empty());
        boolean result = productGateway.alreadyBeenAdded(PRODUCT_ID, CUSTOMER_ID);

        assertFalse(result);

        verify(productRepository).findById(PRODUCT_ENTITY_ID);
        verifyNoMoreInteractions(productRepository);
    }

    @Test
    public void whenSearchThenCallRepository() {

        when(productRepository.findByCustomerId(any(UUID.class), any(PageRequest.class)))
            .thenReturn(new PageImpl<>(List.of(PRODUCT_ENTITY)));
        Set<Product> products = productGateway.search(CUSTOMER_ID, 0, 1);

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals(PRODUCT_ID, ((Product) products.toArray()[0]).getId());

        verify(productRepository).findByCustomerId(CUSTOMER_UUID, PageRequest.of(0, 1));
        verifyNoMoreInteractions(productRepository);
    }
}
