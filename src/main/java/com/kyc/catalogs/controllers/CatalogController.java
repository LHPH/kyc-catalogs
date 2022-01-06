package com.kyc.catalogs.controllers;

import com.kyc.catalogs.command.CatalogManager;
import com.kyc.catalogs.config.CatalogProperties;
import com.kyc.catalogs.model.properties.CatalogInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.kyc.catalogs.constants.Constants.ENDPOINT_CATALOG;
import static com.kyc.catalogs.constants.Constants.ENDPOINT_CATALOG_CRITERIA;

@RestController
public class CatalogController {
	
	public static final Logger LOGGER = LogManager.getLogger(CatalogController.class);
	
	@Autowired
	private CatalogManager catalogManager;

	@Autowired
	private CatalogProperties catalogProperties;
	
	@GetMapping(value=ENDPOINT_CATALOG)
	public ResponseEntity<Object> getCatalog(@PathVariable("catalog") String catalog){
		
		LOGGER.info("Consultando catalogo del servicio de catalogs: [{}]",catalog);
		CatalogInfo catalogInfo = catalogProperties.getCatalog(catalog);
		return catalogManager.getCommand(catalogInfo.getCommand()).invoke(catalogInfo);
	}
	
	@GetMapping(value=ENDPOINT_CATALOG_CRITERIA)
	public ResponseEntity<Object> getCatalogByCriteria(@PathVariable("catalog") String catalog,
			@PathVariable("criteria") String criteria){
		
		LOGGER.info("Consultando catalogo del servicio de catalogs: [{}]",catalog);
		LOGGER.info("Elemento del catalogo a buscar: [{}]",criteria);

		CatalogInfo catalogInfo = catalogProperties.getCatalog(catalog);
		return catalogManager.getCommand(catalogInfo.getCommand()).invoke(catalogInfo,criteria);
	}

}
