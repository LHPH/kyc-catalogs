package com.kyc.catalogs.controllers.delegate;

import com.kyc.catalogs.service.CatalogService;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.model.web.ResponseData;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CatalogControllerDelegateTest {

    @Mock
    private CatalogService catalogService;

    @Mock
    private ResponseData responseData;

    @InjectMocks
    private CatalogDelegate catalogDelegate;

    private RequestData requestData;

    @Before
    public void setUp(){

        requestData = RequestData.builder().build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCatalog_delegateRequest_returnSuccessful(){

        when(catalogService.getCatalog(any(RequestData.class))).thenReturn(responseData);

        catalogDelegate.getCatalog(requestData);
        verify(catalogService,times(1)).getCatalog(requestData);
    }

    @Test
    public void getElementById_delegateRequest_returnSuccessful(){

        when(catalogService.getCatalogElementById(any(RequestData.class))).thenReturn(responseData);

        catalogDelegate.getElementById(requestData);
        verify(catalogService,times(1)).getCatalogElementById(requestData);
    }

    @Test
    public void getCatalogsList_delegateRequest_returnSuccessful(){

        when(catalogService.getListCatalogs()).thenReturn(responseData);

        catalogDelegate.getCatalogsList();
        verify(catalogService,times(1)).getListCatalogs();
    }

    @Test
    public void cleanCache_delegateRequest_returnSuccessful(){

        when(catalogService.cleanCatalogCache()).thenReturn(responseData);

        catalogDelegate.cleanCache();
        verify(catalogService,times(1)).cleanCatalogCache();
    }

}
