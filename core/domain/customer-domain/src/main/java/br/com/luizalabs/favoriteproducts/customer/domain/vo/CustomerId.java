package br.com.luizalabs.favoriteproducts.customer.domain.vo;

import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import java.util.UUID;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CustomerId {

    @EqualsAndHashCode.Include
    private final UUID id;

    private CustomerId() {
        this.id = UUID.randomUUID();
    }

    private CustomerId(final String id) {
        this.id = UUID.fromString(id);
    }

    public static CustomerId newId() {
        return new CustomerId();
    }

    public static CustomerId from(final String id) {

        if (StringUtils.isBlank(id)) {
            throw new IllegalArgumentException("The id cannot be empty");
        }

        return new CustomerId(id);
    }

    public UUID value() {
        return this.id;
    }

    @Override
    public String toString() {
        return this.id.toString();
    }
}
