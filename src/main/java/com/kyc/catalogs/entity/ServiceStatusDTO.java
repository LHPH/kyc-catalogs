package com.kyc.catalogs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="SERVICE_STATUS")
@Setter
@Getter
public class ServiceStatusDTO {
	
	@Id
	@Column(name="id")
	private Integer id;

	@Column(name="DESCRIPTION")
	private String description;
}
