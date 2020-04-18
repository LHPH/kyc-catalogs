package com.kyc.catalogs.model;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServicesVO {
	
	private Integer id;
	private String name;
	private String cost;

}
