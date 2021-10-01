package com.ejemplos.spring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplos.spring.client.CustomerClient;
import com.ejemplos.spring.client.ProductClient;
import com.ejemplos.spring.entity.Invoice;
import com.ejemplos.spring.entity.InvoiceItem;
import com.ejemplos.spring.model.Customer;
import com.ejemplos.spring.model.Product;
import com.ejemplos.spring.repository.InvoiceItemsRepository;
import com.ejemplos.spring.repository.InvoiceRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private InvoiceItemsRepository invoiceItemsRepository;
	
	@Autowired
    CustomerClient customerClient;

    @Autowired
    ProductClient productClient;
	
	@Override
	public List<Invoice> findInvoiceAll() {
		return invoiceRepository.findAll();
	}

	@Override
	public Invoice createInvoice(Invoice invoice) {
		Invoice invoiceDB = invoiceRepository.findByNumberInvoice(invoice.getNumberInvoice());
		if(invoiceDB != null) {
			return invoiceDB;
		}
		invoice.setState("CREATED");
		
		//hay que modificar el stock por cada item de la factura
		invoiceDB = invoiceRepository.save(invoice);
		invoiceDB.getItems().forEach(invoiceItem -> {
			productClient.updateStockProduct(invoiceItem.getProductId(), invoiceItem.getQuantity() * -1);
		});
		//este bucle for each creo que seria como hacerlo asi 
		//for(Invoice inv : invoiceDB){
		//	productClient.updateStockProduct(invoiceItem.getProductId(), invoiceItem.getQuantity() * -1);
		//} obtiene todos los items que se han actualizado, luego con el bucle los recorre uno a uno y va 
		// actualizando el stock
		//se resta la cantidad del producto que se esta usando en la factura
		//digo que se resta porque en el metodo original se suma la quantity, pero como se multiplica por -1,
		//se obtiene un resultado negativo
		return invoiceDB;
	}

	@Override
	public Invoice updateInvoice(Invoice invoice) {
		Invoice invoiceDB = getInvoice(invoice.getId());
		if(invoiceDB == null) {
			return null;
		}
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoiceDB.getItems());
		return invoiceRepository.save(invoiceDB);
	}

	@Override
	public Invoice deleteInvoice(Invoice invoice) {
		Invoice invoiceDB = getInvoice(invoice.getId());
		if(invoiceDB ==  null) {
			return null;
		}
		invoice.setState("DELETED");
		return invoiceRepository.save(invoice);
	}

	@Override
	public Invoice getInvoice(Integer id) {
		Invoice invoice = invoiceRepository.findById(id).orElse(null);
		if(invoice != null) {
			Customer customer = customerClient.getCustomer(invoice.getCustomerId()).getBody();
			invoice.setCustomer(customer);
			List<InvoiceItem> listItem = invoice.getItems().stream()
					.map(invoiceItem -> {
						Product product = productClient.getProduct(invoiceItem.getProductId()).getBody();
						invoiceItem.setProduct(product);
						return invoiceItem;
					}).collect(Collectors.toList());
			//en el map se recupera cada item y se le atribuye un producto
			invoice.setItems(listItem);
		}
		return invoice;
	}

}
