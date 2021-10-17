package br.com.luizalabs.favoriteproducts.product.repository;

import br.com.luizalabs.favoriteproducts.product.entity.ProductEntity;
import br.com.luizalabs.favoriteproducts.product.entity.ProductEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public interface ProductEntityRepository extends JpaRepository<ProductEntity, ProductEntityId> {
}
