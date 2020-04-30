package com.kyc.catalogs.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kyc.catalogs.config.ErrorAttributesConfig;
import com.kyc.catalogs.service.CatalogService;

import static com.kyc.catalogs.constants.Constants.ENDPOINT_CATALOG;
import static com.kyc.catalogs.constants.Constants.ENDPOINT_CATALOG_CRITERIA;

@RestController
public class CatalogController {
	
	public static final Logger LOGGER = LogManager.getLogger(CatalogController.class);
	
	@Autowired
	private CatalogService catalogService;
	
	@GetMapping(value=ENDPOINT_CATALOG)
	public ResponseEntity<Object> getCatalog(@PathVariable("catalog") String catalog){
		
		LOGGER.info("Consultando catalogo del servicio de catalogs: [{}]",catalog);
		
		Object obj=catalogService.getCatalog(catalog);
		return new ResponseEntity<>(obj,obj!=null?HttpStatus.OK:HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value=ENDPOINT_CATALOG_CRITERIA)
	public ResponseEntity<Object> getCatalogByCriteria(@PathVariable("catalog") String catalog,
			@PathVariable("criteria") String criteria){
		
		LOGGER.info("Consultando catalogo del servicio de catalogs: [{}]",catalog);
		LOGGER.info("Elemento del catalogo a buscar: [{}]",criteria);
		
		Object obj=catalogService.getCriteria(catalog, criteria);
		return new ResponseEntity<>(obj,obj!=null?HttpStatus.OK:HttpStatus.NOT_FOUND);
	}

}
