package com.kyc.catalogs.command;

import com.kyc.catalogs.model.properties.CatalogInfo;
import com.kyc.catalogs.model.properties.SqlQueriesInfo;
import com.kyc.catalogs.repository.SimpleSqlRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SqlQueryCatalogCommandTest {

    @Mock
    private SimpleSqlRepository repository;

    @InjectMocks
    private SqlQueryCatalogCommand command;

    private CatalogInfo catalogInfo;

    @BeforeEach
    public void setUp(){

        catalogInfo = new CatalogInfo();
        catalogInfo.setSqlQueries(new SqlQueriesInfo());
        catalogInfo.getSqlQueries().setAllQuery("QUERY");
        catalogInfo.getSqlQueries().setSingleQuery("QUERY_ID");
    }

    @Test
    public void invoke_getCatalog_returnRecords(){

        List<Map<String,Object>> records = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("id","1");
        records.add(map);

        when(repository.getCatalog(catalogInfo.getSqlQueries().getAllQuery())).thenReturn(records);

        List<LinkedHashMap<String,Object>> result = command.invokeList(catalogInfo);
        assertFalse(result.isEmpty());
        assertEquals(map.get("id"),result.get(0).get("id"));
    }

    @Test
    public void invoke_getCatalog_returnEmptyList(){

        List<Map<String,Object>> records = new ArrayList<>();

        when(repository.getCatalog(catalogInfo.getSqlQueries().getAllQuery())).thenReturn(records);

        List<LinkedHashMap<String,Object>> result = command.invokeList(catalogInfo);
        assertTrue(result.isEmpty());
    }

    @Test
    public void invoke_getElementFromCatalog_returnElement(){

        Map<String,Object> map = new HashMap<>();
        map.put("id","1");

        when(repository.getCatalogById(catalogInfo.getSqlQueries().getSingleQuery(),"1")).thenReturn(map);

        LinkedHashMap<String,Object> result = command.invokeSingle(catalogInfo,"1");
        assertEquals(map.get("id"),result.get("id"));
    }

}
