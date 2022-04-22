package com.mx.clip.response;

import java.util.List;

import com.mx.clip.model.PaymentClip;



public class PaymentResponse {
	
	
	private List<PaymentClip> payment;

	public List<PaymentClip> getPayment() {
		return payment;
	}

	public void setPayment(List<PaymentClip> payment) {
		this.payment = payment;
	}

}
