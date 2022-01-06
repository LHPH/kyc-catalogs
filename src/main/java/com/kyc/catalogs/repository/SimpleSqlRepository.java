package com.kyc.catalogs.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Properties;

@Repository
public class SimpleSqlRepository {

    private static final Logger LOGGER = LogManager.getLogger(SimpleSqlRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String,Object>> getCatalog(String query){

        return jdbcTemplate.queryForList(query);
    }

    public Map<String,Object> getCatalogById(String query,String id){

        return jdbcTemplate.queryForMap(query,id);
    }

}
