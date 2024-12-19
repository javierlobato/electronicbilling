package com.electronicbillingmvp.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Invoice invoice;

    @ManyToOne(optional = false)
    private Product product;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    public InvoiceItem() {
    }


    public InvoiceItem(Invoice invoice, Product product, int quantity, BigDecimal unitPrice) {
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        if(unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) <=0){
            throw new IllegalArgumentException("Unit price must be greater than 0");
        }

        this.invoice = invoice;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }


    public Long getId() {
        return id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }


    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        if(quantity <= 0){
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        this.quantity = quantity;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        if(unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) <=0){
            throw new IllegalArgumentException("Unit price must be greater than 0");
        }

        this.unitPrice = unitPrice;
    }


    //Calculate Subtotal
    public BigDecimal calculateSubtotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
