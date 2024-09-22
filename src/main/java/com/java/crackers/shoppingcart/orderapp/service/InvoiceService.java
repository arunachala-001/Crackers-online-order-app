package com.java.crackers.shoppingcart.orderapp.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.java.crackers.shoppingcart.orderapp.model.Customer;
import com.java.crackers.shoppingcart.orderapp.model.Invoice;
import com.java.crackers.shoppingcart.orderapp.model.OrderedProduct;
import com.java.crackers.shoppingcart.orderapp.repository.CustomerRepository;
import com.java.crackers.shoppingcart.orderapp.request.InvoiceRequest;
import com.java.crackers.shoppingcart.orderapp.response.InvoiceProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    @Autowired
    private CustomerRepository customerRepo;


    public byte[] generatePdf(InvoiceRequest invoiceRequest) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Lakshmi Crackers - Sivakasi").setBold().setFontSize(20).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("Invoice").setFontSize(16).setTextAlignment(TextAlignment.CENTER));

        document.add(new Paragraph("Customer Details:").setBold().setUnderline());
        document.add(new Paragraph("Name        : "+ invoiceRequest.getFirstName()+" "+invoiceRequest.getLastName()));
        document.add(new Paragraph("Customer ID : "+ invoiceRequest.getCustomerId().toString()));
        document.add(new Paragraph("Email ID    : "+ invoiceRequest.getEmailId()));
        document.add(new Paragraph("Phone Number: "+invoiceRequest.getMobileNumber()));
        document.add(new Paragraph("Address     : "+ invoiceRequest.getAddress()+" - "+invoiceRequest.getPinCode()));
        document.add(new Paragraph("\n"));

        float[] columnWidths = {100F,100F,100F,100F, 100F};
        Table table = new Table(UnitValue.createPercentArray(columnWidths));

        table.addHeaderCell("S.NO").setBold().setTextAlignment(TextAlignment.CENTER);
        table.addHeaderCell("Product Name").setBold().setTextAlignment(TextAlignment.CENTER);
        table.addHeaderCell("Quantity(no)").setBold().setTextAlignment(TextAlignment.CENTER);
        table.addHeaderCell("Product Price(RS)").setBold().setTextAlignment(TextAlignment.CENTER);
        table.addHeaderCell("Total(Rs)").setBold().setTextAlignment(TextAlignment.CENTER);

        List<OrderedProduct> productList = invoiceRequest.getListofProducts();
        int totalPrice = 0;
        int i = 1;
        for(OrderedProduct ip : productList) {
            table.addCell((String.format(String.valueOf(i++)))).setTextAlignment(TextAlignment.CENTER);
            table.addCell(ip.getProductName()).setTextAlignment(TextAlignment.CENTER);
            table.addCell((String.format(String.valueOf(ip.getQuantity())))).setTextAlignment(TextAlignment.CENTER);
            table.addCell(String.format(String.valueOf(ip.getProductPrice()))).setTextAlignment(TextAlignment.CENTER);
            table.addCell(String.format(String.valueOf(ip.getProductPrice()*ip.getQuantity()))).setTextAlignment(TextAlignment.CENTER);

            totalPrice+=ip.getProductPrice()*ip.getQuantity();
        }
        table.addFooterCell("All Total");
        table.addFooterCell("");
        table.addFooterCell("");
        table.addFooterCell("");
        table.addFooterCell(String.format(String.valueOf(totalPrice)));

        document.add(table);

        document.add(new Paragraph("Contact Info 94427 49794, Depo At : Venkata Sai Traders," +
                "3/1295/9, Sattur Road, Paraipatti, SIVAKASI").setMarginTop(20).setMarginBottom(0));



        document.close();

        return baos.toByteArray();

    }

}
