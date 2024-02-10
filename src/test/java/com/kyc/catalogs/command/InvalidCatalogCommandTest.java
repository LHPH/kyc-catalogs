package com.kyc.catalogs.command;


import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.core.exception.KycRestException;
import com.kyc.core.model.MessageData;
import com.kyc.core.properties.KycMessages;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InvalidCatalogCommandTest {

    @Mock
    private KycMessages kycMessages;

    @InjectMocks
    private InvalidCatalogCommand command;

    @Test(expected = KycRestException.class)
    public void invoke_catalog_throwException(){

        when(kycMessages.getMessage(anyString())).thenReturn(new MessageData());
        command.invokeList(new CatalogInfo());
    }

    @Test(expected = KycRestException.class)
    public void invoke_catalog_criteria_throwException(){

        when(kycMessages.getMessage(anyString())).thenReturn(new MessageData());
        command.invokeSingle(new CatalogInfo(),"1");
    }
}
