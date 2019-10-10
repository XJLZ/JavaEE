package com.dao.DaoImpl;

import com.dao.OrderDao;
import com.domain.Food;
import com.domain.Order;
import com.domain.OrderItem;
import com.util.TemplateSingle;
import com.util.UuidUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class orderDaoImpl implements OrderDao {
    private  JdbcTemplate template = TemplateSingle.getJdbcTemplate();

    /**
     * 根据用户手机查询订单
     * @param phone
     */
    @Override
    public Order findByPhone(String phone) {
        try{
            String sql = "select * from `order` where i_phone = ?";
            return template.queryForObject(sql, new BeanPropertyRowMapper<Order>(Order.class), phone);
        }catch (Exception e){
            return null;
        }

    }


    /**
     * 生成订单
     * @param o
     * @return
     */
    @Override
    public boolean createOrder(Order o) {
        //自提
        if (o.getIAddress()==null){
            DataSourceTransactionManager tran = new DataSourceTransactionManager(template.getDataSource());
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();//事务定义类
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus status = tran.getTransaction(def);
            String insertOrder = "INSERT INTO `order`(o_id,i_username,i_phone,i_way) VALUES(?,?,?,?)";
            try {
                //先有订单  才有订单项
                //连接对象存在问题
                Object[] params = {o.getOId(),o.getIUsername(),o.getIPhone(),o.getIWay()};
                List<Object[]> listParams = new ArrayList<>();
                listParams.add(params);
                template.batchUpdate(insertOrder, listParams);
                String insertOrderItem = "INSERT INTO order_item VALUES(?,?,?,?)";
                List<Object[]> listItemParams = new ArrayList<>();
                for(int i = 0; i < o.getOrderItems().size();i++) {
                    OrderItem item = o.getOrderItems().get(i);
                    Object[] tempParams = {UuidUtil.getUuid(),item.getOId(),item.getFId(),item.getoTotalNet()};
                    listItemParams.add(tempParams);
                }
                template.batchUpdate(insertOrderItem, listItemParams);
                tran.commit(status);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("订单出错01");
                tran.rollback(status);
                System.out.println("订单出错02");
                return false;
            }

        }
        //快递
        else {
            DataSourceTransactionManager tran = new DataSourceTransactionManager(template.getDataSource());
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();//事务定义类
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            TransactionStatus status = tran.getTransaction(def);
            String insertOrder = "INSERT INTO `order`(o_id,i_username,i_phone,i_address,i_way) VALUES(?,?,?,?,?)";
            try {
                //先有订单  才有订单项
                //连接对象存在问题
                Object[] params = {o.getOId(),o.getIUsername(),o.getIPhone(),o.getIAddress(),o.getIWay()};
                List<Object[]> listParams = new ArrayList<>();
                listParams.add(params);
                template.batchUpdate(insertOrder, listParams);
                String insertOrderItem = "INSERT INTO order_item VALUES(?,?,?,?)";
                List<Object[]> listItemParams = new ArrayList<>();
                for(int i = 0; i < o.getOrderItems().size();i++) {
                    OrderItem item = o.getOrderItems().get(i);
                    Object[] tempParams = {UuidUtil.getUuid(),item.getOId(),item.getFId(),item.getoTotalNet()};
                    listItemParams.add(tempParams);
                }
                template.batchUpdate(insertOrderItem, listItemParams);
                tran.commit(status);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("订单出错1");
                tran.rollback(status);
                System.out.println("订单出错2");
                return false;
            }
//            System.out.println("邮寄");
//            System.out.println(o);
//            for (OrderItem orderItem : o.getOrderItems()) {
//                System.out.println(orderItem);
//            }
        }
    }

    /**
     * 根据编号或名称查询菜品
     * @param idOrName
     * @return
     */
    @Override
    public Food findByIDorName(String idOrName) {
        if (idOrName.matches("\\d*")){
            try{
                String sql = "select * from food where f_id = ?";
                return template.queryForObject(sql, new BeanPropertyRowMapper<Food>(Food.class), Integer.parseInt(idOrName));
            }catch (Exception e){
                return null;
            }
        }else {
            try{
                String sql = "select * from food where f_name = ?";
                return template.queryForObject(sql, new BeanPropertyRowMapper<Food>(Food.class), idOrName);
            }catch (Exception e){
                return null;
            }
        }
    }

    /**
     * 订单详情
     * @return
     */
    @Override
    public List<Food> showItems(String oId) {
        try {
            String sql = "SELECT f.* ,o.o_totalnet FROM food f , order_item o WHERE o.f_id = f.f_id AND o.o_id = ?;";
//            List<Food> foods = template.query(sql, new BeanPropertyRowMapper<Food>(Food.class),oId);
//            return foods;
            List<Food> foods = template.query(sql, new RowMapper<Food>() {
                @Override
                public Food mapRow(ResultSet resultSet, int i) throws SQLException {
                       Food food = new Food();
                       food.setFName(resultSet.getString("f_name"));
                       food.setFPrice(resultSet.getDouble("f_price"));
                       food.setTotalNet(resultSet.getDouble("o_totalnet"));
                   return food;
                }
            },oId);
            return foods;
        } catch (Exception e) {
            return null;
        }
    }
}
