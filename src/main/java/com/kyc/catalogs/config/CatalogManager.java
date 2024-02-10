package com.kyc.catalogs.config;

import com.kyc.catalogs.command.CatalogCommand;
import com.kyc.catalogs.command.InvalidCatalogCommand;
import com.kyc.catalogs.enums.CatalogResultType;
import com.kyc.catalogs.model.properties.CatalogInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CatalogManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogManager.class);

    @Autowired
    private ApplicationContext applicationContext;

    public CatalogCommand<Object> getCommand(CatalogInfo catalogInfo, CatalogResultType catalogType) {

        String command = catalogInfo.getCommand();
        String type = catalogInfo.getType();
        LOGGER.info("{} {}",command,type);

        if(command != null){

            boolean sameType = catalogType.name().equalsIgnoreCase(type);
            boolean both = CatalogResultType.BOTH.name().equalsIgnoreCase(type);

            if(both || sameType){

                CatalogCommand<Object> commandBean = (CatalogCommand<Object>) applicationContext.getBean(command);
                return commandBean;
            }
        }
        return applicationContext.getBean(InvalidCatalogCommand.class);
    }

}
