package com.kyc.catalogs.properties;

import com.kyc.catalogs.model.properties.CatalogInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "kyc-config")
@Setter
@Getter
public class CatalogProperties {

    private List<CatalogInfo> catalogs = new ArrayList<>();

    public CatalogInfo getCatalog(String id){

        return catalogs.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElse(new CatalogInfo());
    }

}
