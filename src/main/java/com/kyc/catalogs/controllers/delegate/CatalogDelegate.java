package com.kyc.catalogs.controllers.delegate;

import com.kyc.catalogs.service.CatalogService;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.model.web.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatalogDelegate {

    public static final Logger LOGGER = LoggerFactory.getLogger(CatalogDelegate.class);

    @Autowired
    private CatalogService catalogService;


    public ResponseEntity<ResponseData<List<Object>>> getCatalog(RequestData<Void> req){


        LOGGER.info("Consultando catalogo completo]");
        return catalogService.getCatalog(req).toResponseEntity();
    }

    public ResponseEntity<ResponseData<Object>> getElementById(RequestData<Void> req){

        return catalogService.getCatalogElementById(req).toResponseEntity();
    }

    public ResponseEntity<ResponseData<List<String>>> getCatalogsList(){

        LOGGER.info("Consultando elemento de catalogo");
        return catalogService.getListCatalogs().toResponseEntity();
    }

    public ResponseEntity<ResponseData<Boolean>> cleanCache(){

        return catalogService.cleanCatalogCache().toResponseEntity();
    }

}
