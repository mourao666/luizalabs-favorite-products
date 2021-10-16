package br.com.luizalabs.favoriteproducts.customer.repository;

import br.com.luizalabs.favoriteproducts.customer.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.UUID;

@Named
@ApplicationScoped
public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, UUID> {

    boolean existsByEmail(String email);
}
