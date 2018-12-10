package com.sudhar.example.controller;

import com.sudhar.example.domain.Invoice;
import com.sudhar.example.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RequestMapping(value = "/api/invoice", produces = "application/hal+json")
@ExposesResourceFor(Invoice.class)
@RestController
public class InvoiceController {


    @Autowired
    private InvoiceService service;

    @GetMapping("/customer/{customerId}")
    public Resource<Invoice> getInvoice(@PathVariable final int customerId) {
        final Link link = linkTo(methodOn(InvoiceController.class).getInvoice(customerId)).withSelfRel();

        return invoiceToResource(service.getInvoiceByCustomerId(customerId), link);
    }

    @GetMapping("/all")
    public Resources<Resource<Invoice>> getAllInvoices() {
        final Link selfLink = linkTo(methodOn(InvoiceController.class).getAllInvoices()).withSelfRel();
        return invoiceToResources(service.findAll(), selfLink);
    }

    private static Resources<Resource<Invoice>> invoiceToResources(final List<Invoice> invoices, final Link selfLink) {

        final List<Resource<Invoice>> invoiceResources = invoices.stream().map(invoice -> invoiceToResource(invoice, selfLink)).collect(Collectors.toList());
        return new Resources<>(invoiceResources, selfLink);
    }

    private static Resource<Invoice> invoiceToResource(Invoice invoice, Link link) {
        return new Resource<>(invoice, link);
    }
}
