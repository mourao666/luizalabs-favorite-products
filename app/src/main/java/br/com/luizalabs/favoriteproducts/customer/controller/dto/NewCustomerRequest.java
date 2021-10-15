package br.com.luizalabs.favoriteproducts.customer.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewCustomerRequest {

    private String name;
    private String email;
}
