package com.kyc.catalogs.command;

import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.core.exception.KycRestException;
import org.springframework.http.HttpStatus;
import java.util.List;

public interface CatalogCommand<T> {

    List<T> invoke(CatalogInfo catalogInfo);

    T  invoke(CatalogInfo catalogInfo, Object id);

}
