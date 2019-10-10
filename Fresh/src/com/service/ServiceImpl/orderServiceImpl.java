package com.service.ServiceImpl;

import com.dao.DaoImpl.orderDaoImpl;
import com.dao.OrderDao;
import com.domain.Food;
import com.domain.Order;
import com.domain.OrderItem;
import com.service.orderService;
import com.util.ScannerUtil;
import com.util.UuidUtil;

import java.util.List;
import java.util.Scanner;

public class orderServiceImpl implements orderService {
    private Scanner in = ScannerUtil.getInstance().getIn();
    private OrderDao orderDao = new orderDaoImpl();
    /**
     * 根据用户手机查询订单
     */
    @Override
    public void findByPhone() {
        System.out.println("请输入手机号码:");
        String phone = in.next();
        Order order = orderDao.findByPhone(phone);
        if (order != null){
            show1(order);
            System.out.println("================");
            System.out.println("===1.查看订单===");
            System.out.println("===2.返回上级===");
            System.out.println("===0,退    出===");
            System.out.println("================");
            int key = in.nextInt();
            switch (key){
                case 1:
                    List<Food> foods = orderDao.showItems(order.getOId());
                    showFoods(foods);
                    break;
                case 2:
                    return;
                case 3:
                    System.out.println("退出系统");
                    System.exit(0);
            }
        }else {
            System.out.println("订单不存在!");
        }

    }

    /**
     * 显示订单详情
     * @param foods
     */
    private void showFoods(List<Food> foods) {
        System.out.println("当前位置>>我的订单>>订单详情");
        System.out.println("菜品\t单价\t净含量（g）\t总价");
        for (Food food : foods) {
            double totalPrice = food.getFPrice()*(food.getTotalNet()/500.00);
            System.out.println(food.getFName()+"\t"+food.getFPrice()+"\t"+food.getTotalNet()+"\t\t"+totalPrice);
        }

    }

    /**
     * 显示订单
     * @param order
     */
    private void show1(Order order) {
        System.out.println("当前位置>>我的订单");
        System.out.println("订单编号\t\t创建时间\t\t创建人\t\t联系方式\t联系地址\t派送方式");
        System.out.println(order.getOId()+"\t"+order.getIAddtime()+"\t\t"+order.getIUsername()+"\t\t"+order.getIPhone()
                            +"\t\t"+order.getIAddress()+"\t"+order.getIWay());
    }

    /**
     * 生成订单
     */
    @Override
    public void orderFood() {
        Order order = new Order();
        String o_id = UuidUtil.getUuid();
        while (true){
            System.out.println("=======欢迎订菜=======");
            System.out.println("请输入菜品编号:");
           String fId = in.next();
            Food food = orderDao.findByIDorName(fId);
            if (food != null){

                System.out.println("请输入净含量(g):");
                double net = in.nextDouble();
                System.out.println("您当前购买的是【"+food.getFName()+"】," +
                        "单价是【"+food.getFPrice()+"元/500g】,是否提交订单（Y/N）");
                String choice = in.next();
                OrderItem item = new OrderItem();
                item.setItemId(UuidUtil.getUuid());
                item.setFId(Integer.parseInt(fId));
                item.setOId(o_id);
                item.setoTotalNet(net);
                item.setOTotalprice(food.getFPrice()*(net/500));
                order.getOrderItems().add(item);
                if ("Y".equalsIgnoreCase(choice)){
                    System.out.println("请输入姓名:");
                    String username = in.next();
                    System.out.println("请输入联系方式:");
                    String phone = in.next();
                    System.out.println("请选择派送方式【1.自提 2.邮寄】");
                    int way = in.nextInt();
                    if (1 == way){
                        order.setOId(o_id);
                        order.setIUsername(username);
                        order.setIPhone(phone);
                        order.setIWay("自提");
                        if (orderDao.createOrder(order)) {
                            System.out.println("恭喜你，下单成功！请您于21：00前至【文萃广场店】提货!");
                        }else {
                            System.out.println("服务繁忙！请稍后点菜！");
                        }
                        break;
                    }else {
                        System.out.println("请输入收货地址:");
                        String address = in.next();
                        order.setOId(o_id);
                        order.setIUsername(username);
                        order.setIPhone(phone);
                        order.setIAddress(address);
                        order.setIWay("邮寄");
                        if (orderDao.createOrder(order)) {
                            System.out.println("恭喜你，下单成功！请耐心等待，咱快马加鞭制作中！");
                        }else {
                            System.out.println("服务繁忙！请稍后点菜！");
                        }
                        break;
                    }
                }else {
                    System.out.println("====继续点菜====");
                }
            }else {
                System.out.println("输入编号错误!");
            }
        }
    }

    /**
     * 根据编号或名称查询菜品
     */
    @Override
    public void findByIDorName() {
        System.out.println("请输入编号或名称:");
        String idOrName = in.next();
        Food food = orderDao.findByIDorName(idOrName);
        if (food != null){
            System.out.println("  编号\t菜品\t单价 (500g/元)");
            System.out.println(food);
        }else {
            System.out.println("抱歉！暂无此菜，咱会努力增加菜品的!");
        }

    }
}
