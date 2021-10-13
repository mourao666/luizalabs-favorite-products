package br.com.luizalabs.favoriteproducts.customer.controller.mapper;

import br.com.luizalabs.favoriteproducts.customer.controller.dto.CustomerResponse;
import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomerControllerMapper {

    public static CustomerResponse toDto(final Customer customer) {
        return CustomerResponse.builder()
            .id(customer.getId().toString())
            .name(customer.getName())
            .email(customer.getEmail())
            .build();
    }
}
