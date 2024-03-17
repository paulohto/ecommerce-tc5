package com.ecommercetc5.payment.payment.entity;

import com.ecommercetc5.payment.payment.enums.PaymentFinisher;
import com.ecommercetc5.payment.payment.enums.PaymentType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private User user;
    //private Pedido pedido;
    private Double paymentTotal;

    private PaymentType paymentType;
    //private DataCard dataCard;

    private PaymentFinisher paymentFinisher;

    public Payment() { }
    public Payment(Long id, Double paymentTotal, PaymentType paymentType, PaymentFinisher paymentFinisher) {
        this.id = id;
        this.paymentTotal = paymentTotal;
        this.paymentType = paymentType;
        this.paymentFinisher = paymentFinisher;


    }
}
