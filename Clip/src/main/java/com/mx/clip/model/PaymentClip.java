package com.mx.clip.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "payments")
public class PaymentClip implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6994493479796514393L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long payment_number;

	@NotNull
	private double amount;
/*
	@JsonProperty("terms")
	@Min(4)
	@Max(52)
	@NotNull
	private Integer terms;*/

	//@NotNull
//	private double rate;

	@Column(name = "date_pay")
	@Temporal(TemporalType.DATE)
	private Date payment_date;
	
	
	private String status;
	
	private String user;

	public PaymentClip() {
		super();
	}

	public PaymentClip(Long payment_number, double amount, /*Integer terms,double rate,*/  Date payment_date,String status, String user) {
		super();
		this.payment_number = payment_number;
		this.amount = amount;
		//this.terms = terms;
		//this.rate = rate;
		this.payment_date = payment_date;
		this.status = status;
		this.user = user;
	}

	
	
	
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}

	

	public Long getPayment_number() {
		return payment_number;
	}

	public void setPayment_number(Long payment_number) {
		this.payment_number = payment_number;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
/*
	public Integer getTerms() {
		return terms;
	}

	public void setTerms(Integer terms) {
		this.terms = terms;
	}
	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}*/


}
