package com.kyc.catalogs.command;


import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.core.exception.KycRestException;
import com.kyc.core.model.MessageData;
import com.kyc.core.properties.KycMessages;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InvalidCatalogCommandTest {

    @Mock
    private KycMessages kycMessages;

    @InjectMocks
    private InvalidCatalogCommand command;

    @Test
    public void invoke_catalog_throwException(){

        assertThrows(KycRestException.class,()->{
            when(kycMessages.getMessage(anyString())).thenReturn(new MessageData());
            command.invokeList(new CatalogInfo());
        });

    }

    @Test
    public void invoke_catalog_criteria_throwException(){

        assertThrows(KycRestException.class,()->{
            when(kycMessages.getMessage(anyString())).thenReturn(new MessageData());
            command.invokeSingle(new CatalogInfo(),"1");
        });

    }
}
