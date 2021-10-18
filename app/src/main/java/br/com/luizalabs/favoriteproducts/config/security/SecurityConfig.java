package br.com.luizalabs.favoriteproducts.config.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.oauth2.enable}")
    private Boolean securityEnable;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (Boolean.TRUE.equals(this.securityEnable)) {
            http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();
        }
    }
}
