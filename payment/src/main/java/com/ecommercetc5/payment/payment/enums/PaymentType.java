package com.ecommercetc5.payment.payment.enums;

public enum PaymentType {
    PIX("pix"),
    CREDITO("credito"),
    DEBITO("debito"),
    BOLETO("boleto");

    private String tipo;

    PaymentType(String tipo){
        this.tipo = tipo;
    }

    public String getPaymentType(){
        return tipo;
    }
}
