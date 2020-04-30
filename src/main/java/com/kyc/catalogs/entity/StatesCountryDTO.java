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
@Table(name="STATES_COUNTRY")
public class StatesCountryDTO {

	@Id
	@Column(name="ID")
	private Integer id;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="DESCRIPTION")
	private String description;
}
