package br.com.luizalabs.favoriteproducts.product.repository;

import br.com.luizalabs.favoriteproducts.product.entity.ProductEntity;
import br.com.luizalabs.favoriteproducts.product.entity.ProductEntityId;
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
