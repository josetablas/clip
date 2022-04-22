package com.mx.clip.response;

public class PaymentResponseReporte extends ResponseRest {
	
	private PaymentReporte paymentResponse = new PaymentReporte();

	public PaymentReporte getPaymentReporte() {
		return paymentResponse;
	}

	public void setPaymentReporte(PaymentReporte paymentResponse) {
		this.paymentResponse = paymentResponse;
	}


}
