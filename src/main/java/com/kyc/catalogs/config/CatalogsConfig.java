package com.kyc.catalogs.config;

import com.kyc.core.config.BuildDetailConfig;
import com.kyc.core.exception.KycGenericRestExceptionHandler;
import com.kyc.core.exception.KycUnhandledExceptionHandler;
import com.kyc.core.properties.KycMessages;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

import static com.kyc.catalogs.constants.AppConstants.MESSAGE_001;

@Configuration
@Import(value = {KycMessages.class,KycGenericRestExceptionHandler.class, BuildDetailConfig.class})
@EnableCaching
@EnableScheduling
public class CatalogsConfig {

    @Bean
    public KycUnhandledExceptionHandler kycUnhandledExceptionHandler(KycMessages kycMessages){

        return new KycUnhandledExceptionHandler(kycMessages.getMessage(MESSAGE_001));
    }

}
