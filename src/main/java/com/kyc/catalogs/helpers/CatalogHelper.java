package com.kyc.catalogs.helpers;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.kyc.core.util.GeneralUtil.isMapNotNullAndNotEmpty;

@Component
public class CatalogHelper {

    public LinkedHashMap<String,Object> processMapping(Map<String,Object> row, Map<String,String> fields){

        if(isMapNotNullAndNotEmpty(fields)){

            LinkedHashMap<String,Object> newMap = new LinkedHashMap<>();
            row.forEach((k,v)->newMap.put(fields.getOrDefault(k,k),v));
            return newMap;
        }
        return new LinkedHashMap<>(row);
    }

}
