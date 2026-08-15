package com.kyc.catalogs.properties;

import com.kyc.catalogs.model.properties.CatalogInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@EnableConfigurationProperties(CatalogProperties.class)
@ContextConfiguration(initializers = ConfigDataApplicationContextInitializer.class)
@ActiveProfiles("test")
public class CatalogPropertiesTest {

    @Autowired
    private CatalogProperties catalogProperties;

    @Test
    public void getCatalog_selectElement_returnElement(){

        CatalogInfo result = catalogProperties.getCatalog("kyc-test");
        assertEquals("SQL_TEST",result.getSqlQueries().getAllQuery());
        assertEquals("COMMAND_TEST",result.getCommand());
    }

    @Test
    public void getCatalog_noSelectElement_returnDefault(){

        CatalogInfo result = catalogProperties.getCatalog("kyc-test-1");
        assertNotNull(result);
        assertNull(result.getSqlQueries());
    }

}
