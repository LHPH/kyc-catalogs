package com.kyc.catalogs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="PAYMENT_METHODS")
public class PaymentMethodsDTO {
	
	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="METHOD")
	private String method;

}
