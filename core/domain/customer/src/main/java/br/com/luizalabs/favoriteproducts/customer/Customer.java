package br.com.luizalabs.favoriteproducts.customer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.util.UUID;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {

    @EqualsAndHashCode.Include
    private final UUID id;
    private String name;
    private String email;
    private CustomerStatus status;

    public Customer(String name, String email) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.status = CustomerStatus.ACTIVE;
    }

    public Customer(UUID id, String name, String email, CustomerStatus status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void inactivate() {
        this.status = CustomerStatus.INACTIVE;
    }
}
