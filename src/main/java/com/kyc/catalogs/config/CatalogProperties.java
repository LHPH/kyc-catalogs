package com.kyc.catalogs.config;

import com.kyc.catalogs.model.properties.CatalogInfo;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "kyc-config")
@Data
public class CatalogProperties {

    private List<CatalogInfo> catalogs;

    public CatalogInfo getCatalog(String id){
        return catalogs.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(new CatalogInfo());
    }

}
