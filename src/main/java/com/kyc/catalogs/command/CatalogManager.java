package com.kyc.catalogs.command;

import com.kyc.catalogs.config.CatalogProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CatalogManager {

    @Autowired
    private ApplicationContext applicationContext;


    public CatalogCommand getCommand(String command) {

        if(command != null){
            CatalogCommand commandBean = (CatalogCommand) applicationContext.getBean(command);
            return commandBean;
        }
        return new CatalogCommand() {};
    }

}
