package com.kyc.catalogs.service;

import com.kyc.catalogs.command.CatalogCommand;
import com.kyc.catalogs.config.CatalogManager;
import com.kyc.catalogs.enums.CatalogResultType;
import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.catalogs.properties.CatalogProperties;
import com.kyc.core.exception.KycRestException;
import com.kyc.core.exception.KycSoapException;
import com.kyc.core.model.MessageData;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.model.web.ResponseData;
import com.kyc.core.properties.KycMessages;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.ws.soap.client.SoapFaultClientException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.kyc.catalogs.constants.AppConstants.MESSAGE_001;
import static com.kyc.catalogs.constants.AppConstants.MESSAGE_002;
import static com.kyc.catalogs.constants.AppConstants.PATH_PARAM_CATALOG;
import static com.kyc.catalogs.constants.AppConstants.PATH_PARAM_CRITERIA;

@Service
public class CatalogService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CatalogService.class);

    @Autowired
    private CatalogManager catalogManager;

    @Autowired
    private CatalogProperties catalogProperties;

    @Autowired
    private KycMessages kycMessages;

    @Cacheable(value = "ALL_CATALOG",key = "#req.getPathParams().get('catalog')",condition = "#req.getQueryParams().isEmpty()")
    public ResponseData<List<Object>> getCatalog(RequestData<Void> req){

        Map<String,Object> map = req.getPathParams();
        Map<String, String> params = req.getQueryParams();
        String catalog = map.get(PATH_PARAM_CATALOG).toString();

        CatalogInfo catalogInfo = catalogProperties.getCatalog(catalog);
        LOGGER.info("Consultando catalogo {}",catalog);
        CatalogCommand<Object> command = catalogManager.getCommand(catalogInfo,CatalogResultType.LIST);

        List<Object> result;
        if(CollectionUtils.isEmpty(params)){
            result = command.invokeList(catalogInfo);
        }
        else{
            result = command.invokeList(catalogInfo,params);
        }
        return ResponseData.of(result);
    }

    public ResponseData<Object> getCatalogElementById(RequestData<Void> req){

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

           LOGGER.info("Consultando catalogo {} con criteria {}",catalog,criteria);
           CatalogInfo catalogInfo = catalogProperties.getCatalog(catalog);
           CatalogCommand<Object> command = catalogManager.getCommand(catalogInfo, CatalogResultType.SINGLE);
           Object result = command.invokeSingle(catalogInfo,idRow);
           return ResponseData.of(result);
       }
       catch(EmptyResultDataAccessException empEx){

           throw sendError(MESSAGE_002,HttpStatus.UNPROCESSABLE_ENTITY,null,req);
       }
       catch(KycSoapException soapEx){

           if(soapEx.getErrorData()!=null){

               MessageData messageData = soapEx.getErrorData();
               throw sendError(messageData,HttpStatus.UNPROCESSABLE_ENTITY,soapEx,req);
           }
           throw sendError(MESSAGE_001,HttpStatus.SERVICE_UNAVAILABLE,soapEx,req);
       }
       catch(Exception ex){

           throw sendError(MESSAGE_001,HttpStatus.INTERNAL_SERVER_ERROR,ex,req);
       }
    }

    public ResponseData<List<String>> getListCatalogs(){

        List<String> result = catalogProperties.getCatalogs()
                .stream().map(CatalogInfo::getId)
                .collect(Collectors.toList());

        return ResponseData.of(result);
    }

    @CacheEvict(value = "ALL_CATALOG",allEntries = true)
    @Scheduled(cron = "${kyc-config.cron.clean-cache}")
    public ResponseData<Boolean> cleanCatalogCache(){
        LOGGER.info("Cleans the cache of catalogs");
        return ResponseData.of(true);
    }

    private KycRestException sendError(String code, HttpStatus httpStatus, Exception ex, RequestData<Void> req){

        MessageData message = kycMessages.getMessage(code);
        return sendError(message,httpStatus,ex,req);
    }

    private KycRestException sendError(MessageData messageData, HttpStatus httpStatus, Exception ex, RequestData<Void> req){

        return KycRestException.builderRestException()
                .errorData(messageData)
                .exception(ex)
                .inputData(req)
                .status(httpStatus)
                .inputData(req)
                .build();
    }




}
