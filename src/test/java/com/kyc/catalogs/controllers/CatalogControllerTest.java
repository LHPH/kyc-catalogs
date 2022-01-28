package com.kyc.catalogs.controllers;

import com.kyc.catalogs.controllers.delegate.CatalogDelegate;
import com.kyc.core.model.web.RequestData;
import com.kyc.core.model.web.ResponseData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.kyc.catalogs.constants.AppConstants.ENDPOINT_CATALOG;
import static com.kyc.catalogs.constants.AppConstants.ENDPOINT_CATALOG_CLEAN_CACHE;
import static com.kyc.catalogs.constants.AppConstants.ENDPOINT_CATALOG_CRITERIA;
import static com.kyc.catalogs.constants.AppConstants.ENDPOINT_CATALOG_INDEX;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(controllers = CatalogController.class)
@ActiveProfiles("test")
public class CatalogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatalogDelegate delegate;

    private List<LinkedHashMap<String,Object>> list;
    private LinkedHashMap<String,Object> element;

    @Before
    public void setUp(){

        list = new ArrayList<>();

        element = new LinkedHashMap<>();
        element.put("id","value");

        list.add(element);
    }

    @Test
    public void getCatalog_receiveRequest_returnOk() throws Exception{

        ResponseData<List<LinkedHashMap<String,Object>>> responseData = ResponseData.of(list, HttpStatus.OK);

        given(delegate.getCatalog(any(RequestData.class))).willReturn(responseData.toResponseEntity());

        mockMvc.perform(get(ENDPOINT_CATALOG,"catalog")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void getCatalogByCriteria_receiveRequest_returnOk() throws Exception{

        ResponseData<LinkedHashMap<String,Object>> responseData = ResponseData.of(element,HttpStatus.OK);

        given(delegate.getElementById(any(RequestData.class))).willReturn(responseData.toResponseEntity());

        mockMvc.perform(get(ENDPOINT_CATALOG_CRITERIA,"catalog","1")).andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void getCatalogsList_receiveRequest_returnOk() throws Exception{

        ResponseData<List<String>> responseData = ResponseData.of(new ArrayList<>(),HttpStatus.OK);

        given(delegate.getCatalogsList()).willReturn(responseData.toResponseEntity());

        mockMvc.perform(get(ENDPOINT_CATALOG_INDEX)).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void cleanCache_receiveRequest_returnOk() throws Exception{

        ResponseData<Boolean> responseData = ResponseData.of(true,HttpStatus.OK);

        given(delegate.cleanCache()).willReturn(responseData.toResponseEntity());

        mockMvc.perform(post(ENDPOINT_CATALOG_CLEAN_CACHE)).andDo(print()).andExpect(status().isOk());
    }


}
