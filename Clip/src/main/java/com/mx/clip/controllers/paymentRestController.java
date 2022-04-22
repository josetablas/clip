package com.mx.clip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.clip.model.PaymentClip;
import com.mx.clip.response.PaymentResponseReporte;
import com.mx.clip.response.PaymentResponseRest;
import com.mx.clip.service.IPaymentClipService;

@RestController
@RequestMapping("/v1")
public class paymentRestController {

	@Autowired
	private IPaymentClipService ipaymentClipService;

	@PostMapping("/paymentClip")
	public ResponseEntity<PaymentResponseRest> generatePayment(@RequestBody PaymentClip request) {

		ResponseEntity<PaymentResponseRest> response = ipaymentClipService.generatePayment(request);

		return response;

	}

	@GetMapping("/payments")
	public ResponseEntity<?> getPayments() {

		ResponseEntity<PaymentResponseRest> response = ipaymentClipService.listPayments();

		return response;

	}

	@GetMapping("/paymentsUser")
	public ResponseEntity<?> getPaymentsUser(@RequestBody PaymentClip user) {

		ResponseEntity<PaymentResponseRest> response = ipaymentClipService.listPaymentsUsers(user);

		return response;

	}

	@PutMapping("/paymentDesenbolso/{id}")
	public ResponseEntity<PaymentResponseRest> updatePayment(@PathVariable Long id) {

		ResponseEntity<PaymentResponseRest> response = ipaymentClipService.aplicaDesenbolso(/* request, */ id);

		return response;

	}

	@GetMapping("/paymentsReporte")
	public ResponseEntity<?> getPaymentsReporte() {

		ResponseEntity<PaymentResponseReporte> response = ipaymentClipService.listPaymentsReporte();

		return response;

	}

}
