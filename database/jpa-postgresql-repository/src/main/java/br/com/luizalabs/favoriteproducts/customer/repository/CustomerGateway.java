package br.com.luizalabs.favoriteproducts.customer.repository;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.repository.entity.CustomerEntity;
import br.com.luizalabs.favoriteproducts.customer.repository.entity.CustomerEntityRepository;
import br.com.luizalabs.favoriteproducts.customer.repository.entity.CustomerEntityStatus;
import br.com.luizalabs.favoriteproducts.customer.repository.mapper.CustomerMapper;
import br.com.luizalabs.favoriteproducts.customer.usecase.port.Customers;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Optional;

@Named
@ApplicationScoped
public class CustomerGateway implements Customers {

    private final CustomerEntityRepository repository;

    @Inject
    public CustomerGateway(final CustomerEntityRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer createOrUpdate(Customer customer) {
        CustomerEntity entity = CustomerMapper.toEntity(customer);
        entity = repository.save(entity);
        return CustomerMapper.toDomain(entity);
    }

    @Override
    public Optional<Customer> find(CustomerId id) {
        return repository.findByIdAndStatus(id.value(), CustomerEntityStatus.ACTIVE).map(CustomerMapper::toDomain);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return repository.findByEmail(email).map(CustomerMapper::toDomain);
    }

    @Override
    public boolean emailAlreadyExists(String email) {
        return repository.existsByEmail(email);
    }
}
