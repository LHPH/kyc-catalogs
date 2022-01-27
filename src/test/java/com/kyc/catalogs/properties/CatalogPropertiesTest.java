package com.kyc.catalogs.properties;

import com.kyc.catalogs.model.properties.CatalogInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@EnableConfigurationProperties(CatalogProperties.class)
@ContextConfiguration(initializers = ConfigFileApplicationContextInitializer.class)
@ActiveProfiles("test")
public class CatalogPropertiesTest {

    @Autowired
    private CatalogProperties catalogProperties;

    @Test
    public void getCatalog_selectElement_returnElement(){

        CatalogInfo result = catalogProperties.getCatalog("kyc-test");
        Assert.assertEquals("SQL_TEST",result.getSqlQuery());
        Assert.assertEquals("COMMAND_TEST",result.getCommand());
    }

    @Test
    public void getCatalog_noSelectElement_returnDefault(){

        CatalogInfo result = catalogProperties.getCatalog("kyc-test-1");
        Assert.assertNotNull(result);
        Assert.assertNull(result.getSqlQuery());
    }

}