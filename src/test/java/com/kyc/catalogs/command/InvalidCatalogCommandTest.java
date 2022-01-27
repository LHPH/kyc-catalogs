package com.kyc.catalogs.command;


import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.core.exception.KycRestException;
import com.kyc.core.model.web.MessageData;
import com.kyc.core.properties.KycMessages;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvalidCatalogCommandTest {

    @Mock
    private KycMessages kycMessages;

    @InjectMocks
    private InvalidCatalogCommand command;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = KycRestException.class)
    public void invoke_catalog_throwException(){

        when(kycMessages.getMessage(anyString())).thenReturn(new MessageData());
        command.invoke(new CatalogInfo());
    }

    @Test(expected = KycRestException.class)
    public void invoke_catalog_criteria_throwException(){

        when(kycMessages.getMessage(anyString())).thenReturn(new MessageData());
        command.invoke(new CatalogInfo(),"1");
    }
}
