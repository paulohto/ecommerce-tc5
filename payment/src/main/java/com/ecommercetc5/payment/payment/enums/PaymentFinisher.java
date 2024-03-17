package com.ecommercetc5.payment.payment.enums;

public enum PaymentFinisher {
    CONCLUIR("concluir"),
    DESISTIR("desistir");

    private String type;

    PaymentFinisher(String type){
        this.type = type;
    }

    public String getPaymentFinisher(){
        return type;
    }
}
