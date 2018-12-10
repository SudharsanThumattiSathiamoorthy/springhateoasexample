package com.sudhar.example.controller;

import com.sudhar.example.domain.Customer;
import com.sudhar.example.domain.Invoice;
import com.sudhar.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/customers", produces = "application/hal+json")
@ExposesResourceFor(Customer.class)
public class CustomerController {

    @Autowired
    private CustomerService service;


    @Autowired
    private EntityLinks entityLinks;

    @RequestMapping(method = RequestMethod.GET)
    public Resources<Resource<Customer>> getCustomers() {
        return toCustomerResource(service.getCustomerList());
    }

    private Resources<Resource<Customer>> toCustomerResource(List<Customer> customerList) {
        Link selfLink = linkTo(methodOn(CustomerController.class).getCustomers()).withSelfRel();

        List<Resource<Customer>> customerResource = customerList.stream().map(customer -> customerToResource(customer)).collect(Collectors.toList());
        return new Resources<>(customerResource, selfLink);
    }

    @RequestMapping("/{id}")
    public Resource<Customer> getCustomer(@PathVariable int id) {
        return customerToResource(service.getById(id));
    }

    @RequestMapping("/{name}")
    public Resource<Customer> getCustomer(@PathVariable String name) {
        return customerToResource(service.getCustomerList().get(1));
    }

    private Resource<Customer> customerToResource(Customer customer) {
        Link selfLink = linkTo(methodOn(CustomerController.class).getCustomer(customer.getId())).withSelfRel();

        Link allInvoiceLink = entityLinks.linkToCollectionResource(Invoice.class).withRel("all-invoices");
       // Link nameLink = linkTo(methodOn(CustomerController.class).getCustomer(customer.getFirstName())).withRel("name");
        return new Resource<>(customer, selfLink, allInvoiceLink);
    }
}
