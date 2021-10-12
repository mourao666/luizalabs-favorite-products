package br.com.luizalabs.favoriteproducts.customer.domain;

import br.com.luizalabs.favoriteproducts.customer.domain.exception.InvalidCustomerEmailException;
import br.com.luizalabs.favoriteproducts.customer.domain.exception.InvalidCustomerNameException;
import br.com.luizalabs.favoriteproducts.customer.domain.exception.InvalidCustomerStatusException;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {

    @EqualsAndHashCode.Include
    private final CustomerId id;
    private String name;
    private String email;
    private CustomerStatus status;

    public Customer(final String name, final String email) {

        this.validateName(name);
        this.validateEmail(email);

        this.id = CustomerId.newId();
        this.name = name;
        this.email = email;
        this.status = CustomerStatus.ACTIVE;
    }

    public Customer(final CustomerId id, final String name, final String email, final CustomerStatus status) {

        this.validateName(name);
        this.validateEmail(email);

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
        this.validateEmail(email);
        this.email = email;
    }

    private void validateEmail(final String email) {
        if (!EmailValidator.getInstance().isValid(email)) {
            throw new InvalidCustomerEmailException();
        }
    }

    public void inactivate() {

        if (CustomerStatus.INACTIVE.equals(this.status)) {
            throw new InvalidCustomerStatusException();
        }

        this.status = CustomerStatus.INACTIVE;
    }
}
