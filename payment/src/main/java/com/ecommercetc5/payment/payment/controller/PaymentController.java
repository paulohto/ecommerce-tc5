package com.ecommercetc5.payment.payment.controller;

import com.ecommercetc5.payment.payment.dto.PaymentDTO;
import com.ecommercetc5.payment.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentDTO> paymentFinisher(@Valid @RequestBody PaymentDTO paymentDTO){
        var paymentFinished = paymentService.paymentFinisher(paymentDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(paymentFinished.id()).toUri();
        return ResponseEntity.created(uri).body(paymentFinished);
    }

}
