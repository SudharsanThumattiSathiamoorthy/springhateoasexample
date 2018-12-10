package com.sudhar.example.service;

import com.sudhar.example.domain.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
public class InvoiceService {

    private final List<Invoice> invoices;

    @Autowired
    public InvoiceService() {
        invoices = new LinkedList<>();
        invoices.add(new Invoice(UUID.randomUUID(), 10, "title1", 1));
        invoices.add(new Invoice(UUID.randomUUID(), 20, "title2", 2));
        invoices.add(new Invoice(UUID.randomUUID(), 30, "title3", 3));
        invoices.add(new Invoice(UUID.randomUUID(), 40, "title4", 4));
    }

    public Invoice getInvoiceByCustomerId(int customerId) {
        return invoices.stream().filter(invoice -> invoice.getCustomerId() == customerId).findAny().get();
    }

    public Invoice getInvoiceByTitle(String title) {
        return invoices.stream().filter(invoice -> invoice.getTitle().equals(title)).findFirst().get();
    }

    public List<Invoice> findAll() {
        return List.copyOf(invoices);
    }
}
