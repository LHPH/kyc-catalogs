package com.kyc.catalogs.model.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogInfo {

    private String id;
    private String sqlQuery;
    private String sqlQueryId;
    private String command;

}
