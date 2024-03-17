package com.ecommercetc5.payment.payment.enums;

public enum PaymentType {
    PIX("pix"),
    CREDITO("credito"),
    DEBITO("debito"),
    BOLETO("boleto");

    private String type;

    PaymentType(String type){
        this.type = type;
    }

    public String getPaymentType(){
        return type;
    }
}
