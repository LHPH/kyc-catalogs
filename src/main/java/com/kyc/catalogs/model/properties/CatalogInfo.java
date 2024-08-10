package com.kyc.catalogs.model.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogInfo {

    private String id;
    private String type;
    private SqlQueriesInfo sqlQueries;
    private String command;

}
