package com.kyc.catalogs.service;

import com.kyc.catalogs.command.CatalogCommand;
import com.kyc.catalogs.config.CatalogManager;
import com.kyc.catalogs.enums.CatalogResultType;
import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.catalogs.properties.CatalogProperties;
import com.kyc.core.exception.KycRestException;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.model.web.ResponseData;
import com.kyc.core.properties.KycMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kyc.catalogs.constants.AppConstants.PATH_PARAM_CATALOG;
import static com.kyc.catalogs.constants.AppConstants.PATH_PARAM_CRITERIA;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatalogServiceTest {

    @Mock
    private CatalogManager catalogManager;

    @Mock
    private CatalogProperties catalogProperties;

    @Mock
    private KycMessages kycMessages;

    @Mock
    private CatalogCommand<Object> command;

    @InjectMocks
    private CatalogService catalogService;

    private RequestData<Void> request;
    private CatalogInfo catalogInfo;

    private static final String CATALOG_VALUE = "catalog";
    private static final String CATALOG_CRITERIA_VALUE ="1";
    private static final String COMMAND = "command";

    @BeforeEach
    public void setUp(){

        Map<String,Object> params = new HashMap<>();
        params.put(PATH_PARAM_CATALOG,CATALOG_VALUE);
        params.put(PATH_PARAM_CRITERIA,CATALOG_CRITERIA_VALUE);

        request = RequestData.<Void>builder().pathParams(params).build();

        catalogInfo = new CatalogInfo();
        catalogInfo.setCommand(COMMAND);
        catalogInfo.setType(CatalogResultType.LIST.name().toLowerCase());
    }

    @Test
    public void getCatalog_retrieveCatalog_returnCatalog(){

        when(catalogProperties.getCatalog(CATALOG_VALUE)).thenReturn(catalogInfo);
        when(catalogManager.getCommand(catalogInfo, CatalogResultType.LIST)).thenReturn(command);
        when(command.invokeList(catalogInfo)).thenReturn(new ArrayList<>());

        ResponseData<List<Object>> result = catalogService.getCatalog(request);

        assertNotNull(result);
        assertNotNull(result.getData());
        assertEquals(HttpStatus.OK,result.getHttpStatus());
    }

    @Test
    public void getCatalogElementById_retrieveElementCriteriaNumeric_returnElement(){

        Integer id = 1;
        Map<String,Object> params = this.request.getPathParams();
        params.replace(PATH_PARAM_CRITERIA,id);

        RequestData<Void>  request = RequestData.<Void>builder()
                .pathParams(params)
                .build();

        catalogInfo.setType(CatalogResultType.SINGLE.name().toLowerCase());

        when(catalogProperties.getCatalog(CATALOG_VALUE)).thenReturn(catalogInfo);
        when(catalogManager.getCommand(catalogInfo,CatalogResultType.SINGLE)).thenReturn(command);
        when(command.invokeSingle(catalogInfo,id)).thenReturn(new Object());

        ResponseData<Object> result = catalogService.getCatalogElementById(request);

        assertNotNull(result);
        assertNotNull(result.getData());
        assertEquals(HttpStatus.OK,result.getHttpStatus());

    }

    @Test
    public void getCatalogElementById_retrieveElementCriteriaString_returnElement(){

        String id = "ab";
        Map<String,Object> params = this.request.getPathParams();
        params.replace(PATH_PARAM_CRITERIA,id);

        RequestData<Void>  request = RequestData.<Void>builder()
                .pathParams(params)
                .build();

        catalogInfo.setType(CatalogResultType.SINGLE.name().toLowerCase());

        when(catalogProperties.getCatalog(CATALOG_VALUE)).thenReturn(catalogInfo);
        when(catalogManager.getCommand(catalogInfo,CatalogResultType.SINGLE)).thenReturn(command);
        when(command.invokeSingle(catalogInfo,id)).thenReturn(new Object());

        ResponseData<Object> result = catalogService.getCatalogElementById(request);

        assertNotNull(result);
        assertNotNull(result.getData());
        assertEquals(HttpStatus.OK,result.getHttpStatus());
    }

    @Test
    public void getCatalogElementById_retrieveZeroResults_returnException(){

        try{

            Integer id = 1;
            Map<String,Object> params = this.request.getPathParams();
            params.replace(PATH_PARAM_CRITERIA,id);

            RequestData<Void>  request = RequestData.<Void>builder()
                    .pathParams(params)
                    .build();

            catalogInfo.setType(CatalogResultType.SINGLE.name().toLowerCase());

            when(catalogProperties.getCatalog(CATALOG_VALUE)).thenReturn(catalogInfo);
            when(catalogManager.getCommand(catalogInfo,CatalogResultType.SINGLE)).thenReturn(command);
            when(command.invokeSingle(catalogInfo,id)).thenThrow(new EmptyResultDataAccessException("zero",1));

            catalogService.getCatalogElementById(request);
            fail("Should not pass");
        }
        catch(KycRestException ex){

            assertEquals(HttpStatus.UNPROCESSABLE_ENTITY,ex.getStatus());
        }
    }

    @Test
    public void getCatalogElementById_errorOnDatabase_returnException(){

        try{

            Integer id = 1;
            Map<String,Object> params = this.request.getPathParams();
            params.replace(PATH_PARAM_CRITERIA,id);

            RequestData<Void>  request = RequestData.<Void>builder()
                    .pathParams(params)
                    .build();

            catalogInfo.setType(CatalogResultType.SINGLE.name().toLowerCase());

            when(catalogProperties.getCatalog(CATALOG_VALUE)).thenReturn(catalogInfo);
            when(catalogManager.getCommand(catalogInfo,CatalogResultType.SINGLE)).thenReturn(command);
            when(command.invokeSingle(catalogInfo,id)).thenThrow(new InvalidDataAccessResourceUsageException("invalid sql"));

            catalogService.getCatalogElementById(request);
            fail("Should not pass");
        }
        catch(KycRestException ex){

            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,ex.getStatus());
        }

    }

    @Test
    public void cleanCatalogCache_cleaningCache_returnSuccess(){

        ResponseData<Boolean> result = catalogService.cleanCatalogCache();
        assertNotNull(result);
    }

    @Test
    public void getListCatalogs_retrieveList_returnSuccess(){

        CatalogInfo catalogInfo = new CatalogInfo();
        catalogInfo.setId("test");

        when(catalogProperties.getCatalogs()).thenReturn(Collections.singletonList(catalogInfo));
        ResponseData<List<String>> result = catalogService.getListCatalogs();
        assertNotNull(result);
        assertEquals(catalogInfo.getId(),result.getData().get(0));
    }

}
