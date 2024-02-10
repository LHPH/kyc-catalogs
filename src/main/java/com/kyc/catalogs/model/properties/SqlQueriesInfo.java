package com.kyc.catalogs.model.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SqlQueriesInfo {

    private String allQuery;
    private String singleQuery;
}
