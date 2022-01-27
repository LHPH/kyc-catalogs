package com.kyc.catalogs.command;

import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.core.exception.KycRestException;
import com.kyc.core.properties.KycMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.kyc.catalogs.constants.AppConstants.MESSAGE_003;

@Component
public class InvalidCatalogCommand implements CatalogCommand<Object>{

    @Autowired
    private KycMessages kycMessages;

    public List<Object> invoke(CatalogInfo catalogInfo){
        throw KycRestException.builder()
                .outputData("Not Implemented")
                .errorData(kycMessages.getMessage(MESSAGE_003))
                .status(HttpStatus.NOT_IMPLEMENTED)
                .build();
    }

    public Object  invoke(CatalogInfo catalogInfo, Object id){
        throw KycRestException.builder()
                .outputData("Not Implemented")
                .errorData(kycMessages.getMessage(MESSAGE_003))
                .status(HttpStatus.NOT_IMPLEMENTED)
                .build();
    }
}
