package com.java.crackers.shoppingcart.orderapp.controller;


import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.java.crackers.shoppingcart.orderapp.model.Customer;
import com.java.crackers.shoppingcart.orderapp.model.OrderedProduct;
import com.java.crackers.shoppingcart.orderapp.repository.CustomerRepository;
import com.java.crackers.shoppingcart.orderapp.repository.OrderRepository;
import com.java.crackers.shoppingcart.orderapp.request.InvoiceRequest;
import com.java.crackers.shoppingcart.orderapp.response.InvoiceProductResponse;
import com.java.crackers.shoppingcart.orderapp.response.ProductResponse;
import com.java.crackers.shoppingcart.orderapp.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    @Autowired
    private final InvoiceService invoiceService;

    @Autowired
    CustomerRepository customerRepo;

    @Autowired
    OrderRepository orderRepo;

    @Transactional
    @PostMapping("/download/{custId}")
    public ResponseEntity<byte[]> generateInvoice(@PathVariable UUID custId )  {
        Customer customer = customerRepo.findById(custId).orElseThrow();

        InvoiceRequest invoiceRequest = InvoiceRequest.builder()
                .customerId(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .emailId(customer.getEmailAddress())
                .mobileNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .pinCode(customer.getPinCode())
                .ListofProducts(customer.getOrderedProductList())
                .build();
        byte[] pdfBytes = invoiceService.generatePdf(invoiceRequest);

        if(pdfBytes == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "invoice.pdf");

        return ResponseEntity.ok()
                     .headers(headers)
                     .body(pdfBytes);

    }

}
