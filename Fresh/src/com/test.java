package com;

import com.domain.Order;
import com.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from `order` where i_phone = ?";
        Object[] params = new Object[] { "55555" };
        Order order = template.queryForObject(sql,params, new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                Order order = null;
                try {
                    order = new Order();
                    order.setOId(resultSet.getString("o_id"));
                    String date = resultSet.getString("i_addtime");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date time = sdf.parse(date);
                    order.setIAddtime(time);
                    order.setIUsername(resultSet.getString("i_username"));
                    order.setIPhone(resultSet.getString("i_phone"));
                    order.setIAddress(resultSet.getString("i_address"));
                    order.setIWay(resultSet.getString("i_way"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return order;
            }
        });

//        Order order = template.queryForObject(sql, new BeanPropertyRowMapper<Order>(Order.class), "55555");
        System.out.println(order);
    }
}
