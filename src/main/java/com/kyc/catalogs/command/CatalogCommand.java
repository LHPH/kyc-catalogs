package com.kyc.catalogs.command;

import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.core.model.web.RequestData;

import java.util.List;
import java.util.Map;

public interface CatalogCommand<T> {

    default List<T> invokeList(CatalogInfo catalogInfo){
        throw new UnsupportedOperationException("Not Implemented");
    }

    default List<T> invokeList(CatalogInfo catalogInfo, Map<String,String> filter){
        throw new UnsupportedOperationException("Not Implemented");
    }

    default T  invokeSingle(CatalogInfo catalogInfo, Object element){
        throw new UnsupportedOperationException("Not Implemented");
    }
}
