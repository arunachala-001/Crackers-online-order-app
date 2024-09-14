package com.java.crackers.shoppingcart.orderapp.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.java.crackers.shoppingcart.orderapp.request.InvoiceRequest;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class InvoiceService {


    public byte[] generatePdf(InvoiceRequest invoiceRequest) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("Lakshmi Crackers - Sivakasi").setBold().setFontSize(20).setTextAlignment(TextAlignment.CENTER));
        document.add(new Paragraph("Invoice").setFontSize(16).setTextAlignment(TextAlignment.CENTER));

        document.add(new Paragraph("Customer Details:").setBold().setUnderline());
        document.add(new Paragraph("Name: "+ invoiceRequest.getFirstName()+" "+invoiceRequest.getLastName()));
        document.add(new Paragraph("Customer ID: "+ invoiceRequest.getCustomerId()));
        document.add(new Paragraph("Email ID: "+ invoiceRequest.getEmailId()));
        document.add(new Paragraph("Phone Number: "+invoiceRequest.getMobileNumber()));
        document.add(new Paragraph("Address: "+ invoiceRequest.getAddress()+" - "+invoiceRequest.getPinCode()));
        document.add(new Paragraph("\n"));

        float[] columnWidths = {100F,100F,100F,100F, 100F};
        Table table = new Table(UnitValue.createPercentArray(columnWidths));
//        table.setWidth(UnitValue.createPercentValue(100));

        table.addHeaderCell("Product ID").setBold().setTextAlignment(TextAlignment.CENTER);
        table.addHeaderCell("Product Name").setBold().setTextAlignment(TextAlignment.CENTER);
        table.addHeaderCell("Quantity(no)").setBold().setTextAlignment(TextAlignment.CENTER);
        table.addHeaderCell("Product Price(₹)").setBold().setTextAlignment(TextAlignment.CENTER);
        table.addHeaderCell("Total(₹)").setBold().setTextAlignment(TextAlignment.CENTER);

        int price = invoiceRequest.getPrice()* invoiceRequest.getQuantity();

        table.addCell(invoiceRequest.getProductId().toString()).setTextAlignment(TextAlignment.CENTER);
        table.addCell(invoiceRequest.getProductName()).setTextAlignment(TextAlignment.CENTER);
        table.addCell((String.format(String.valueOf(invoiceRequest.getQuantity())))).setTextAlignment(TextAlignment.CENTER);
        table.addCell(String.format(String.valueOf(invoiceRequest.getPrice()))).setTextAlignment(TextAlignment.CENTER);
        table.addCell(String.format(String.valueOf(price))).setTextAlignment(TextAlignment.CENTER);

        document.add(new Paragraph("Contact Info 94427 49794, Depo At : Venkata Sai Traders," +
                "3/1295/9, Sattur Road, Paraipatti, SIVAKASI").setMarginBottom(5));

        document.add(table);
        document.close();

        return baos.toByteArray();

    }
}
