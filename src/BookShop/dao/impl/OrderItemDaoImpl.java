package BookShop.dao.impl;

import BookShop.dao.OrderItemDao;
import BookShop.pojo.OrderItem;

import java.util.List;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao
{
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`)values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String orderId) {
        String sql = "select * from t_order_item join t_order on t_order_item.`order_id` = t_order.`order_id` where t_order.`order_id` = ?";
        return queryForList(OrderItem.class,sql,orderId);
    }
}
