package com.kyc.catalogs.config;

import com.kyc.core.exception.KycGenericRestExceptionHandler;
import com.kyc.core.exception.KycUnhandledExceptionHandler;
import com.kyc.core.properties.KycMessages;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@Import(value = {KycMessages.class,KycGenericRestExceptionHandler.class})
@EnableCaching
@EnableScheduling
public class CatalogsConfig {

    @Bean
    public KycUnhandledExceptionHandler kycUnhandledExceptionHandler(KycMessages kycMessages){

        return new KycUnhandledExceptionHandler(kycMessages.getMessage("001"));
    }

}
