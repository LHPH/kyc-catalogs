package com.kyc.catalogs.config;

import com.kyc.catalogs.command.CatalogCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CatalogManager {

    @Autowired
    private ApplicationContext applicationContext;

    public CatalogCommand<Object> getCommand(String command) {

        if(command != null){
            CatalogCommand<Object> commandBean = (CatalogCommand<Object>) applicationContext.getBean(command);
            return commandBean;
        }
        return new CatalogCommand<Object>() {};
    }

}
