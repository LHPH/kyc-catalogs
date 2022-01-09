package com.kyc.catalogs.command;

import com.kyc.catalogs.model.properties.CatalogInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CatalogCommand<T> {

    default List<T> invoke(CatalogInfo catalogInfo){
        throw new UnsupportedOperationException("Not Implemented");
    }

    default T  invoke(CatalogInfo catalogInfo, Object id){
        throw new UnsupportedOperationException("Not Implemented");
    }

}
