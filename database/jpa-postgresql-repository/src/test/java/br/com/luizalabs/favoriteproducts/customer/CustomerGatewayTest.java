package br.com.luizalabs.favoriteproducts.customer;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.CustomerStatus;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.entity.CustomerEntity;
import br.com.luizalabs.favoriteproducts.customer.entity.CustomerEntityStatus;
import br.com.luizalabs.favoriteproducts.customer.repository.CustomerEntityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerGatewayTest {

    private static final String STRING_ID = "123e4567-a456-42d3-e89b-556642440000";
    private static final String EMAIL = "dummy@testmail.com";

    private static final UUID ID = UUID.fromString(STRING_ID);
    private static final CustomerId CUSTOMER_ID = CustomerId.from(STRING_ID);

    private static final Customer CUSTOMER = new Customer(CUSTOMER_ID, "Dummy", EMAIL, CustomerStatus.ACTIVE);
    private static final CustomerEntity CUSTOMER_ENTITY = new CustomerEntity(ID, "Dummy", EMAIL, CustomerEntityStatus.ACTIVE, null);

    @Mock
    private CustomerEntityRepository repository;

    @InjectMocks
    private CustomerGateway customerGateway;

    @Test
    public void whenCreateOrUpdateThenCallRepository() {

        when(repository.save(any(CustomerEntity.class))).thenReturn(CUSTOMER_ENTITY);
        Customer customer = customerGateway.createOrUpdate(CUSTOMER);
        assertNotNull(customer);

        ArgumentCaptor<CustomerEntity> captor = ArgumentCaptor.forClass(CustomerEntity.class);
        verify(repository).save(captor.capture());
        verifyNoMoreInteractions(repository);

        CustomerEntity customerEntity = captor.getValue();
        assertNotNull(customerEntity);
        assertEquals(customer.getId().value(), customerEntity.getId());
    }

    @Test
    public void whenFindThenCallRepository() {

        when(repository.findByIdAndStatus(any(UUID.class), any(CustomerEntityStatus.class))).thenReturn(Optional.of(CUSTOMER_ENTITY));
        Optional<Customer> optionalCustomer = customerGateway.find(CUSTOMER_ID);

        assertTrue(optionalCustomer.isPresent());
        assertEquals(ID, optionalCustomer.get().getId().value());

        verify(repository).findByIdAndStatus(ID, CustomerEntityStatus.ACTIVE);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void whenFindByEmailThenCallRepository() {

        when(repository.findByEmail(anyString())).thenReturn(Optional.of(CUSTOMER_ENTITY));
        Optional<Customer> optionalCustomer = customerGateway.findByEmail(EMAIL);

        assertTrue(optionalCustomer.isPresent());
        assertEquals(ID, optionalCustomer.get().getId().value());

        verify(repository).findByEmail(EMAIL);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void whenEmailAlreadyExistsThenCallRepository() {

        when(repository.existsByEmail(EMAIL)).thenReturn(true);
        boolean result = customerGateway.emailAlreadyExists(EMAIL);

        assertTrue(result);

        verify(repository).existsByEmail(EMAIL);
        verifyNoMoreInteractions(repository);
    }

    @Test
    public void whenEmailDoesNotExistsThenCallRepository() {

        when(repository.existsByEmail(EMAIL)).thenReturn(false);
        boolean result = customerGateway.emailAlreadyExists(EMAIL);

        assertFalse(result);

        verify(repository).existsByEmail(EMAIL);
        verifyNoMoreInteractions(repository);
    }
}
