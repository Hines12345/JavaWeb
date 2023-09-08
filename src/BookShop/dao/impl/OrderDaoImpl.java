package BookShop.dao.impl;

import BookShop.dao.OrderDao;
import BookShop.pojo.Order;
import BookShop.pojo.OrderItem;
import BookShop.pojo.User;


import java.util.Date;
import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order (`order_id` ,`create_time`,`price`,`status`,`user_id`) values (?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrders() {
        String sql = "select * from t_order ";
        return queryForList(Order.class,sql);
    }

    @Override
    public int changeOrderStatus(Integer status, String orderId) {
        String sql = "UPDATE t_order SET `status`=? WHERE `order_id`=?;";
        return update(sql,status,orderId);
    }

    @Override
    public Order queryOrderByUserId(Integer userId) {
        String sql = "select `order_id`,`create_time`,`price`,`status`,`user_id` from t_order join t_user on t_order.`user_id` = t_user.`id` where t_user.`id` = ?";
        return queryForOne(Order.class,sql,userId);
    }
}
