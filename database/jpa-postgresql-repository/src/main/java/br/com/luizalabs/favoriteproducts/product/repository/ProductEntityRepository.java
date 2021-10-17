package br.com.luizalabs.favoriteproducts.product.repository;

import br.com.luizalabs.favoriteproducts.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Optional;
import java.util.UUID;

@Named
@ApplicationScoped
public interface ProductEntityRepository extends JpaRepository<ProductEntity, UUID> {

    void deleteByIdAndCustomerId(UUID productId, UUID customerId);

    Optional<ProductEntity> findByIdAndCustomerId(UUID productId, UUID customerId);
}
