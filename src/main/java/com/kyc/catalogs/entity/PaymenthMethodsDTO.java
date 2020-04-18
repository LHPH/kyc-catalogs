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
@Table(name="PAYMENTH_METHODS")
public class PaymenthMethodsDTO {
	
	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="METHOD")
	private String method;
	
	@Column(name="GENERIC_REFERENCE")
	private String genericReference;
	

}
