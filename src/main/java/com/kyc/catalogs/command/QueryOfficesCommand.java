package com.kyc.catalogs.command;

import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.catalogs.repository.SimpleSqlRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component("QUERY_OFFICES_COMMAND")
public class QueryOfficesCommand extends SqlQueryCatalogCommand{

    @Autowired
    private SimpleSqlRepository repository;

    @Override
    public List<LinkedHashMap<String, Object>> invokeList(CatalogInfo catalogInfo) {
        return invokeList(catalogInfo, new HashMap<>());
    }

    @Override
    public List<LinkedHashMap<String, Object>> invokeList(CatalogInfo catalogInfo, Map<String, String> filter) {

        Integer id = NumberUtils.toInt(filter.get("id"),0);
        String city = ObjectUtils.defaultIfNull(filter.get("city"),"");
        String state = ObjectUtils.defaultIfNull(filter.get("state"),"");
        String postalCode = ObjectUtils.defaultIfNull(filter.get("postal_code"),"");
        Boolean active = Boolean.parseBoolean(filter.getOrDefault("active","true"));

        List<Map<String,Object>> map = repository.getCatalog(catalogInfo.getSqlQueries().getAllQuery(),id,city,state,postalCode,active);

        List<LinkedHashMap<String,Object>> listRecords = new ArrayList<>();
        map.forEach(row -> listRecords.add(new LinkedHashMap<>(row)));
        return listRecords;
    }
}
