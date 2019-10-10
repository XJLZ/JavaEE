package com.util;

import org.springframework.jdbc.core.JdbcTemplate;


public class TemplateSingle {

    private static JdbcTemplate template = null;

    public static JdbcTemplate getJdbcTemplate(){
        if (template == null){
            template = new JdbcTemplate(JDBCUtils.getDataSource());
        }
        return template;
    }

    private TemplateSingle(){}

}
