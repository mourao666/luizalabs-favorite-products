package br.com.luizalabs.favoriteproducts.customer;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.entity.CustomerEntity;
import br.com.luizalabs.favoriteproducts.customer.mapper.CustomerMapper;
import br.com.luizalabs.favoriteproducts.customer.repository.CustomerEntityRepository;
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
    public void delete(CustomerId id) {
        repository.deleteById(id.value());
    }

    @Override
    public Optional<Customer> find(CustomerId id) {
        return repository.findById(id.value()).map(CustomerMapper::toDomain);
    }

    @Override
    public boolean exists(CustomerId id) {
        return repository.existsById(id.value());
    }

    @Override
    public boolean emailAlreadyExists(String email) {
        return repository.existsByEmail(email);
    }
}
