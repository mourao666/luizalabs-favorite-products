package br.com.luizalabs.favoriteproducts.customer.controller;

import br.com.luizalabs.favoriteproducts.customer.controller.dto.CustomerResponse;
import br.com.luizalabs.favoriteproducts.customer.controller.dto.NewCustomerRequest;
import br.com.luizalabs.favoriteproducts.customer.controller.dto.UpdateCustomerRequest;
import br.com.luizalabs.favoriteproducts.customer.controller.mapper.CustomerControllerMapper;
import br.com.luizalabs.favoriteproducts.customer.domain.Customer;
import br.com.luizalabs.favoriteproducts.customer.domain.vo.CustomerId;
import br.com.luizalabs.favoriteproducts.customer.usecase.CreateCustomer;
import br.com.luizalabs.favoriteproducts.customer.usecase.DeleteCustomer;
import br.com.luizalabs.favoriteproducts.customer.usecase.FindCustomer;
import br.com.luizalabs.favoriteproducts.customer.usecase.UpdateCustomer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import javax.inject.Inject;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CreateCustomer createCustomer;
    private final DeleteCustomer deleteCustomer;
    private final FindCustomer findCustomer;
    private final UpdateCustomer updateCustomer;

    @Inject
    public CustomerController(CreateCustomer createCustomer, DeleteCustomer deleteCustomer,
                              FindCustomer findCustomer, UpdateCustomer updateCustomer) {
        this.createCustomer = createCustomer;
        this.deleteCustomer = deleteCustomer;
        this.findCustomer = findCustomer;
        this.updateCustomer = updateCustomer;
    }

    @PostMapping
    public CustomerResponse create(@RequestBody NewCustomerRequest request) {
        Customer customer = createCustomer.create(request.getName(), request.getEmail());
        return CustomerControllerMapper.toDto(customer);
    }

    @GetMapping("/{id}")
    public CustomerResponse find(@PathVariable String id) {
        Customer customer = findCustomer.findOne(CustomerId.from(id));
        return CustomerControllerMapper.toDto(customer);
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable String id, @RequestBody UpdateCustomerRequest request) {
        Customer customer = updateCustomer.update(CustomerId.from(id), request.getName(), request.getEmail());
        return CustomerControllerMapper.toDto(customer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        deleteCustomer.delete(CustomerId.from(id));
    }

    @GetMapping("/{email}/by-email")
    public CustomerResponse findByEmail(@PathVariable String email) {
        Customer customer = findCustomer.findByEmail(email);
        return CustomerControllerMapper.toDto(customer);
    }
}
