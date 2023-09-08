package BookShop.dao;

import BookShop.pojo.Order;
import BookShop.pojo.OrderItem;
import BookShop.pojo.User;

import java.util.List;

public interface OrderDao {
    //1、保存订单
    public int saveOrder(Order order);

    //2、查询全部订单
    public List<Order> queryOrders();

    //3、修改订单状态
    public int changeOrderStatus(Integer status,String orderId);

    //4、根据用户查询订单信息
    public Order queryOrderByUserId(Integer userId);
}
