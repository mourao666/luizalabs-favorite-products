package br.com.luizalabs.favoriteproducts.product.controller;

import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.product.controller.dto.AddProductRequest;
import br.com.luizalabs.favoriteproducts.product.controller.dto.ProductResponse;
import br.com.luizalabs.favoriteproducts.product.controller.mapper.ProductControllerMapper;
import br.com.luizalabs.favoriteproducts.product.domain.Product;
import br.com.luizalabs.favoriteproducts.product.domain.vo.ProductId;
import br.com.luizalabs.favoriteproducts.product.usecase.AddProduct;
import br.com.luizalabs.favoriteproducts.product.usecase.RemoveProduct;
import br.com.luizalabs.favoriteproducts.product.usecase.SearchProducts;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.inject.Inject;
import java.util.Set;

@RestController
@RequestMapping("/customers/{customerId}/products")
public class ProductController {

    private final AddProduct addProduct;
    private final RemoveProduct removeProduct;
    private final SearchProducts searchProducts;

    @Inject
    public ProductController(AddProduct addProduct, RemoveProduct removeProduct, SearchProducts searchProducts) {
        this.addProduct = addProduct;
        this.removeProduct = removeProduct;
        this.searchProducts = searchProducts;
    }

    @PostMapping
    public ProductResponse add(@PathVariable String customerId, @RequestBody AddProductRequest request) {
        Product product = addProduct.add(ProductId.from(request.getProductId()), CustomerId.from(customerId));
        return ProductControllerMapper.toDto(product);
    }

    @GetMapping
    public Set<ProductResponse> search(@PathVariable String customerId) {
        Set<Product> productsSet = searchProducts.search(CustomerId.from(customerId));
        return ProductControllerMapper.toDtoSet(productsSet);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remove(@PathVariable String customerId, @PathVariable String productId) {
        removeProduct.remove(ProductId.from(productId), CustomerId.from(customerId));
    }
}
