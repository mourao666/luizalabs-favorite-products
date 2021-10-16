package br.com.luizalabs.favoriteproducts.customer.mapper;

import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.CustomerStatus;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.entity.CustomerEntity;
import br.com.luizalabs.favoriteproducts.customer.entity.CustomerEntityStatus;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerMapper {

    public static CustomerEntity toEntity(final Customer customer) {
        return CustomerEntity.builder()
            .id(customer.getId().value())
            .name(customer.getName())
            .email(customer.getEmail())
            .status(CustomerEntityStatus.valueOf(customer.getStatus().name()))
            .build();
    }

    public static Customer toDomain(final CustomerEntity customerEntity) {
        return new Customer(
            CustomerId.from(customerEntity.getId().toString()),
            customerEntity.getName(),
            customerEntity.getEmail(),
            CustomerStatus.valueOf(customerEntity.getStatus().name())
        );
    }
}
