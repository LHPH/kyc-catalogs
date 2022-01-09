package com.kyc.catalogs.command;

import com.kyc.catalogs.helpers.CatalogHelper;
import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.catalogs.repository.SimpleSqlRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpStatus;
import sun.awt.image.ImageWatched;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

@Component("SQL_QUERY_COMMAND")
public class SqlQueryCatalogCommand implements CatalogCommand<LinkedHashMap<String,Object>>{

    public static final Logger LOGGER = LogManager.getLogger(SqlQueryCatalogCommand.class);

    @Autowired
    private CatalogHelper catalogHelper;

    @Autowired
    private SimpleSqlRepository repository;

    @Override
    public List<LinkedHashMap<String,Object>> invoke(CatalogInfo catalogInfo) {

        List<Map<String,Object>> map = repository.getCatalog(catalogInfo.getSqlQuery());
        Map<String,String> fields = catalogInfo.getFields();

        List<LinkedHashMap<String,Object>> listRecords = new ArrayList<>();
        map.forEach(row -> listRecords.add(catalogHelper.processMapping(row,fields)));
        return listRecords;
    }

    @Override
    public LinkedHashMap<String, Object> invoke(CatalogInfo catalogInfo, Object id) {

        Map<String, Object> row = repository.getCatalogById(catalogInfo.getSqlQueryId(),id);
        Map<String, String> fields = catalogInfo.getFields();
        return catalogHelper.processMapping(row, fields);
    }
}
