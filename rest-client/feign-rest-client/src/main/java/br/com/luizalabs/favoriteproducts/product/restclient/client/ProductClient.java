package br.com.luizalabs.favoriteproducts.product.restclient.client;

import br.com.luizalabs.favoriteproducts.product.restclient.dto.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
@FeignClient(name = "productClient", url = "${feign.client.product.url}", decode404 = true)
public interface ProductClient {

    @GetMapping("/{id}/")
    ProductResponse getProduct(@PathVariable("id") String id);
}
