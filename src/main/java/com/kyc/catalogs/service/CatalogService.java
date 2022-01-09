package com.kyc.catalogs.service;

import com.kyc.catalogs.command.CatalogManager;
import com.kyc.catalogs.command.SqlQueryCatalogCommand;
import com.kyc.catalogs.config.CatalogProperties;
import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.core.exception.KycRestException;
import com.kyc.core.model.web.MessageData;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.model.web.ResponseData;
import com.kyc.core.properties.KycMessages;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.kyc.catalogs.constants.AppConstants.MESSAGE_001;
import static com.kyc.catalogs.constants.AppConstants.MESSAGE_002;
import static com.kyc.catalogs.constants.AppConstants.PATH_PARAM_CATALOG;
import static com.kyc.catalogs.constants.AppConstants.PATH_PARAM_CRITERIA;

@Service
public class CatalogService {

    @Autowired
    private CatalogManager catalogManager;

    @Autowired
    private CatalogProperties catalogProperties;

    @Autowired
    private KycMessages kycMessages;

    public ResponseData<List<LinkedHashMap<String,Object>>> getCatalog(RequestData<Void> req){

        Map<String,Object> map = req.getPathParams();
        String catalog = map.get(PATH_PARAM_CATALOG).toString();

        CatalogInfo catalogInfo = catalogProperties.getCatalog(catalog);
        SqlQueryCatalogCommand command = (SqlQueryCatalogCommand) catalogManager.getCommand(catalogInfo.getCommand());
        List<LinkedHashMap<String,Object>> result = command.invoke(catalogInfo);
        return ResponseData.of(result);
    }

    public ResponseData<LinkedHashMap<String,Object>> getCatalogElementById(RequestData<Void> req){

        Map<String,Object> map = req.getPathParams();
        String catalog = map.get(PATH_PARAM_CATALOG).toString();
        String criteria = map.get(PATH_PARAM_CRITERIA).toString();

        Object idRow;
        if(StringUtils.isNumeric(criteria)){
            idRow = Integer.parseInt(criteria);
        }
        else{
            idRow = criteria;
        }

       try{
           CatalogInfo catalogInfo = catalogProperties.getCatalog(catalog);
           SqlQueryCatalogCommand command = (SqlQueryCatalogCommand) catalogManager.getCommand(catalogInfo.getCommand());
           LinkedHashMap<String,Object> result = command.invoke(catalogInfo,idRow);
           return ResponseData.of(result);
       }
       catch(EmptyResultDataAccessException empEx){

           throw sendError(MESSAGE_002,HttpStatus.NOT_FOUND,null,req);
       }
       catch(Exception ex){

           throw sendError(MESSAGE_001,HttpStatus.INTERNAL_SERVER_ERROR,ex,req);
       }
    }

    private KycRestException sendError(String code, HttpStatus httpStatus, Exception ex, RequestData<Void> req){

        MessageData message = kycMessages.getMessage(code);
        return KycRestException.builder()
                .errorData(message)
                .exception(ex)
                .inputData(req)
                .status(httpStatus)
                .inputData(req)
                .build();
    }


}
