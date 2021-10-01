package com.ejemplos.spring.service;

import java.util.List;

import com.ejemplos.spring.entity.Invoice;

public interface InvoiceService {

	public List<Invoice> findInvoiceAll();
    public Invoice createInvoice(Invoice invoice);
    public Invoice updateInvoice(Invoice invoice);
    public Invoice deleteInvoice(Invoice invoice);
    public Invoice getInvoice(Integer id);
	
}
