package br.com.luizalabs.favoriteproducts.product.repository.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.UUID;

@Named
@ApplicationScoped
public interface ProductEntityRepository extends JpaRepository<ProductEntity, ProductEntityId> {

    Page<ProductEntity> findByCustomerId(UUID customerId, Pageable pageable);
}
