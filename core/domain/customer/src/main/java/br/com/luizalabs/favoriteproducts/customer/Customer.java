package br.com.luizalabs.favoriteproducts.customer;

import br.com.luizalabs.favoriteproducts.customer.exception.InvalidCustomerNameException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import java.util.UUID;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {

    @EqualsAndHashCode.Include
    private final UUID id;
    private String name;
    private String email;
    private CustomerStatus status;

    public Customer(final String name, final String email) {

        this.validateName(name);

        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.status = CustomerStatus.ACTIVE;
    }

    public Customer(final UUID id, final String name, final String email, final CustomerStatus status) {

        this.validateName(name);

        this.id = id;
        this.name = name;
        this.email = email;
        this.status = status;
    }

    public void updateName(final String name) {
        this.validateName(name);
        this.name = name;
    }

    private void validateName(final String name) {
        if (StringUtils.isBlank(name)) {
            throw new InvalidCustomerNameException();
        }
    }

    public void updateEmail(final String email) {
        this.email = email;
    }

    public void inactivate() {
        this.status = CustomerStatus.INACTIVE;
    }
}
