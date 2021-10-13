package br.com.luizalabs.favoriteproducts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "br.com.luizalabs.favoriteproducts" })
public class FavoriteProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FavoriteProductsApplication.class, args);
    }
}
