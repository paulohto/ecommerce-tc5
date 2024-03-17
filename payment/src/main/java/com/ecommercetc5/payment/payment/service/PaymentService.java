package com.ecommercetc5.payment.payment.service;

import com.ecommercetc5.payment.payment.dto.PaymentDTO;
import com.ecommercetc5.payment.payment.entity.Payment;
import com.ecommercetc5.payment.payment.repository.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class PaymentService {

    @Autowired
    private IPaymentRepository paymentRepository;

    public PaymentDTO paymentFinisher(PaymentDTO paymentDTO){
        var paymentEntity = PaymentDTO.toEntity(paymentDTO);
        var paymentSaved = paymentRepository.save(paymentEntity);
        return PaymentDTO.fromEntity(paymentSaved);
    }

}
