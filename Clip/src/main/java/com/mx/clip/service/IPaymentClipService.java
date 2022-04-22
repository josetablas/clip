package com.mx.clip.service;

import org.springframework.http.ResponseEntity;

import com.mx.clip.model.PaymentClip;
import com.mx.clip.response.PaymentResponseReporte;
import com.mx.clip.response.PaymentResponseRest;

public interface IPaymentClipService {

	public ResponseEntity<PaymentResponseRest> generatePayment(PaymentClip payment);

	public ResponseEntity<PaymentResponseRest> listPayments();

	public ResponseEntity<PaymentResponseRest> listPaymentsUsers(PaymentClip payment);

	public ResponseEntity<PaymentResponseRest> aplicaDesenbolso(Long id);

	public ResponseEntity<PaymentResponseReporte> listPaymentsReporte();

}
