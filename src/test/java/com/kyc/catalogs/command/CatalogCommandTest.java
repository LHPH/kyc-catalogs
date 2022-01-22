package com.kyc.catalogs.command;


import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.core.exception.KycRestException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CatalogCommandTest {

    @Test(expected = KycRestException.class)
    public void invoke_catalog_throwException(){

        CatalogCommand<String> command = new CatalogCommand<String>() {};
        command.invoke(new CatalogInfo());
    }

    @Test(expected = KycRestException.class)
    public void invoke_catalog_criteria_throwException(){

        CatalogCommand<String> command = new CatalogCommand<String>() {};
        command.invoke(new CatalogInfo(),"1");
    }
}
