package com.kyc.catalogs.controllers.delegate;

import com.kyc.catalogs.command.CatalogManager;
import com.kyc.catalogs.config.CatalogProperties;
import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.catalogs.service.CatalogService;
import com.kyc.core.model.web.RequestData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.kyc.core.model.web.ResponseData;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class CatalogDelegate {

    public static final Logger LOGGER = LogManager.getLogger(CatalogDelegate.class);

    @Autowired
    private CatalogService catalogService;


    public ResponseEntity<ResponseData<List<LinkedHashMap<String,Object>>>> getCatalog(RequestData<Void> req){

        Map<String,Object> map = req.getPathParams();
        String catalog = map.get("catalog").toString();

        LOGGER.info("Consultando catalogo del servicio de catalogs: [{}]",catalog);
        return catalogService.getCatalog(req).toResponseEntity();
    }

    public ResponseEntity<ResponseData<LinkedHashMap<String,Object>>> getElementById(RequestData<Void> req){

        Map<String,Object> map = req.getPathParams();
        String catalog = map.get("catalog").toString();
        String criteria = map.get("criteria").toString();

        LOGGER.info("Consultando catalogo del servicio de catalogs: [{}]",catalog);
        LOGGER.info("Elemento del catalogo a buscar: [{}]",criteria);

        return catalogService.getCatalogElementById(req).toResponseEntity();
    }

}
