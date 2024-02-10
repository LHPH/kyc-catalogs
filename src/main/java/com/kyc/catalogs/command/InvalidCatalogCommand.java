package com.kyc.catalogs.command;

import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.core.exception.KycRestException;
import com.kyc.core.properties.KycMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static com.kyc.catalogs.constants.AppConstants.MESSAGE_003;

@Component
public class InvalidCatalogCommand implements CatalogCommand<Object>{

    @Autowired
    private KycMessages kycMessages;

    @Override
    public List<Object> invokeList(CatalogInfo catalogInfo) {
        return invokeList(catalogInfo,null);
    }

    @Override
    public List<Object> invokeList(CatalogInfo catalogInfo, Map<String,String> filter){
        throw KycRestException.builderRestException()
                .outputData("Not Implemented")
                .errorData(kycMessages.getMessage(MESSAGE_003))
                .status(HttpStatus.NOT_IMPLEMENTED)
                .build();
    }

    @Override
    public Object  invokeSingle(CatalogInfo catalogInfo, Object id){
        throw KycRestException.builderRestException()
                .outputData("Not Implemented")
                .errorData(kycMessages.getMessage(MESSAGE_003))
                .status(HttpStatus.NOT_IMPLEMENTED)
                .build();
    }
}
