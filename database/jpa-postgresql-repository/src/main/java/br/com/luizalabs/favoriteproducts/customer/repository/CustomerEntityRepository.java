package br.com.luizalabs.favoriteproducts.customer.repository;

import br.com.luizalabs.favoriteproducts.customer.entity.CustomerEntity;
import br.com.luizalabs.favoriteproducts.customer.entity.CustomerEntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Optional;
import java.util.UUID;

@Named
@ApplicationScoped
public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, UUID> {

    boolean existsByEmail(String email);

    Optional<CustomerEntity> findByIdAndStatus(UUID id, CustomerEntityStatus status);

    Optional<CustomerEntity> findByEmail(String email);
}
