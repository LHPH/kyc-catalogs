package com.kyc.catalogs.controllers;

import com.kyc.catalogs.controllers.delegate.CatalogDelegate;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.model.web.ResponseData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.kyc.catalogs.constants.AppConstants.ENDPOINT_CATALOG;
import static com.kyc.catalogs.constants.AppConstants.ENDPOINT_CATALOG_CLEAN_CACHE;
import static com.kyc.catalogs.constants.AppConstants.ENDPOINT_CATALOG_CRITERIA;
import static com.kyc.catalogs.constants.AppConstants.ENDPOINT_CATALOG_INDEX;
import static com.kyc.catalogs.constants.AppConstants.PATH_PARAM_CATALOG;
import static com.kyc.catalogs.constants.AppConstants.PATH_PARAM_CRITERIA;

@RestController
public class CatalogController {
	
	public static final Logger LOGGER = LogManager.getLogger(CatalogController.class);
	
	@Autowired
	private CatalogDelegate delegate;
	
	@GetMapping(value=ENDPOINT_CATALOG)
	public ResponseEntity<ResponseData<List<Object>>> getCatalog(@PathVariable("catalog") String catalog){

		RequestData<Void> req = RequestData.<Void>builder()
				.pathParams(Collections.singletonMap(PATH_PARAM_CATALOG,catalog))
				.build();

		return getDelegate().getCatalog(req);
	}
	
	@GetMapping(value=ENDPOINT_CATALOG_CRITERIA)
	public ResponseEntity<ResponseData<Object>> getCatalogByCriteria(@PathVariable("catalog") String catalog,
			@PathVariable("criteria") String criteria){

		Map<String,Object> map = new HashMap<>();
		map.put(PATH_PARAM_CATALOG,catalog);
		map.put(PATH_PARAM_CRITERIA,criteria);

		RequestData<Void> req = RequestData.<Void>builder()
				.pathParams(map)
				.build();

		return getDelegate().getElementById(req);

	}

	@GetMapping(value = ENDPOINT_CATALOG_INDEX)
	public ResponseEntity<ResponseData<List<String>>> getCatalogsList(){

		return getDelegate().getCatalogsList();
	}

	@PostMapping(value = ENDPOINT_CATALOG_CLEAN_CACHE)
	public ResponseEntity<ResponseData<Boolean>> cleanCache(){

		return getDelegate().cleanCache();
	}


	public CatalogDelegate getDelegate(){
		return delegate;
	}
}
