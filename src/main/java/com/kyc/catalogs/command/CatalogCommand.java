package com.kyc.catalogs.command;

import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.core.exception.KycRestException;
import org.springframework.http.HttpStatus;
import java.util.List;

public interface CatalogCommand<T> {

    default List<T> invoke(CatalogInfo catalogInfo){
        throw KycRestException.builder()
                .outputData("Not Implemented")
                .status(HttpStatus.NOT_IMPLEMENTED)
                .build();
    }

    default T  invoke(CatalogInfo catalogInfo, Object id){
        throw KycRestException.builder()
                .outputData("Not Implemented")
                .status(HttpStatus.NOT_IMPLEMENTED)
                .build();
    }

}
