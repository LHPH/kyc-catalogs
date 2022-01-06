package com.kyc.catalogs.command;

import com.kyc.catalogs.model.properties.CatalogInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface CatalogCommand {

    default ResponseEntity<Object> invoke(CatalogInfo catalogInfo){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

    default ResponseEntity<Object>  invoke(CatalogInfo catalogInfo, String id){
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }

}
