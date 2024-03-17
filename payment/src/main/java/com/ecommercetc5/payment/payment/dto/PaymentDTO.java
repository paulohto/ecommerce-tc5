package com.ecommercetc5.payment.payment.dto;

import com.ecommercetc5.payment.payment.entity.DataCard;
import com.ecommercetc5.payment.payment.entity.Payment;
import com.ecommercetc5.payment.payment.enums.PaymentFinisher;
import com.ecommercetc5.payment.payment.enums.PaymentType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public record PaymentDTO (

    Long id,
    //User user;
    //pPedido pedido;
    Double paymentTotal,

    PaymentType paymentType,
    //DataCard dataCard,
    PaymentFinisher paymentFinisher

) {
    public PaymentDTO (Payment payment){
        this(
                payment.getId(),
                payment.getPaymentTotal(),
                payment.getPaymentType(),
                //payment.getDataCard(),
                payment.getPaymentFinisher()
        );
    }

    public static PaymentDTO fromEntity(Payment entity){
        return new PaymentDTO(
                entity.getId(),
                entity.getPaymentTotal(),
                entity.getPaymentType(),
                //entity.getDataCard(),
                entity.getPaymentFinisher()
        );
    }

    public static Payment toEntity(PaymentDTO dto){
        return new Payment(
                dto.id,
                dto.paymentTotal,
                dto.paymentType,
                //dto.dataCard,
                dto.paymentFinisher
        );
    }

    public static Payment mapperDTOtoEntity(PaymentDTO dto, Payment entity){
        entity.setId(dto.id);
        entity.setPaymentTotal(dto.paymentTotal);
        entity.setPaymentType(dto.paymentType);
        //entity.setDataCard(dto.dataCard);
        entity.setPaymentFinisher(dto.paymentFinisher);
        return entity;
    }
}
