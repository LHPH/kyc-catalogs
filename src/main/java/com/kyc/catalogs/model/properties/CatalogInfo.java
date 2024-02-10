package com.kyc.catalogs.model.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CatalogInfo {

    private String id;
    private String type;
    private SqlQueriesInfo sqlQueries;
    private String command;

}
