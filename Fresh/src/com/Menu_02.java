package com;

import com.domain.Food;
import com.service.ServiceImpl.orderServiceImpl;
import com.service.orderService;
import com.util.ScannerUtil;
import com.util.TemplateSingle;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Scanner;

public class Menu_02 {
    private static orderService service = new orderServiceImpl();
    public static void Start() {
        Scanner in = ScannerUtil.getInstance().getIn();
        while (true){
            showFood();
            try {
                int key = in.nextInt();
                switch (key){
                    case 1:
                        service.findByPhone();
                        break;
                    case 2:
                        service.orderFood();
                        break;
                    case 3:
                        service.findByIDorName();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("输入错误");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("输入格式错误!");
            }
        }
    }
    private static void showFood() {
        JdbcTemplate template = TemplateSingle.getJdbcTemplate();
        String sql = "select * from food";
        List<Food> foods = template.query(sql, new BeanPropertyRowMapper<Food>(Food.class));
        System.out.println("============天天生鲜============\n"+
                           "**********今日可订菜品**********");
        System.out.println("  编号\t菜品\t单价 (500g/元)");
        for (Food food : foods) {
            System.out.println(food);
        }
        System.out.println("======1.我的订单======\n" +
                           "======2.我要订菜======\n" +
                           "======3.查询菜品======\n"+
                           "======4.注    销======");
                }


}
