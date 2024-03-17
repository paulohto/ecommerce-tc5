package com.ecommercetc5.payment.payment.enums;

public enum PaymentFinisher {
    CONCLUIR("concluir"),
    DESISTIR("desistir");

    private String finish;

    PaymentFinisher(String finish){
        this.finish = finish;
    }

    public String getPaymentFinisher(){
        return finish;
    }
}
