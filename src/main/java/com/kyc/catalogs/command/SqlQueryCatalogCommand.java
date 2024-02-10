package com.kyc.catalogs.command;

import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.catalogs.repository.SimpleSqlRepository;
import com.kyc.core.model.web.RequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component("SQL_QUERY_COMMAND")
public class SqlQueryCatalogCommand implements CatalogCommand<LinkedHashMap<String,Object>>{

    public static final Logger LOGGER = LoggerFactory.getLogger(SqlQueryCatalogCommand.class);

    @Autowired
    private SimpleSqlRepository repository;

    @Override
    public List<LinkedHashMap<String,Object>> invokeList(CatalogInfo catalogInfo) {

        LOGGER.info("Query in Database");
        List<Map<String,Object>> map = repository.getCatalog(catalogInfo.getSqlQueries().getAllQuery());

        List<LinkedHashMap<String,Object>> listRecords = new ArrayList<>();
        map.forEach(row -> listRecords.add(new LinkedHashMap<>(row)));
        return listRecords;
    }

    @Override
    public LinkedHashMap<String, Object> invokeSingle(CatalogInfo catalogInfo, Object id) {

        LOGGER.info("Query in Database");
        Map<String, Object> row = repository.getCatalogById(catalogInfo.getSqlQueries().getSingleQuery(),id);
        return new LinkedHashMap<>(row);
    }
}
