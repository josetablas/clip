package com.mx.clip.model;

public class PaymentReporteRespuesta {
	
	private String user;
	private double sumaPagos;
	private double pagosNuevos;
	private double importe;
	
	
	public PaymentReporteRespuesta(){
		
	}
	
	
	public PaymentReporteRespuesta(String user, double sumaPagos, double pagosNuevos, double importe) {
		super();
		this.user = user;
		this.sumaPagos = sumaPagos;
		this.pagosNuevos = pagosNuevos;
		this.importe = importe;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public double getSumaPagos() {
		return sumaPagos;
	}
	public void setSumaPagos(double sumaPagos) {
		this.sumaPagos = sumaPagos;
	}
	public double getPagosNuevos() {
		return pagosNuevos;
	}
	public void setPagosNuevos(double pagosNuevos) {
		this.pagosNuevos = pagosNuevos;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	

}
