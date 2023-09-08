package BookShop.service.impl;

import BookShop.dao.BookDao;
import BookShop.dao.OrderDao;
import BookShop.dao.OrderItemDao;
import BookShop.dao.impl.BookImpl;
import BookShop.dao.impl.OrderDaoImpl;
import BookShop.dao.impl.OrderItemDaoImpl;
import BookShop.pojo.*;
import BookShop.service.OrderService;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.awt.print.Book;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    OrderItemDao orderItemDao = new OrderItemDaoImpl();


    /*------------------------------------生成订单-------------------------------------------*/
    @Override
    public String createOrder(Cart cart, Integer userId) {
        OrderItemDao orderItemDao = new OrderItemDaoImpl();

        //、怎么保障每一张订单的订单号不一样：通过时间戳+上用户的id
        String orderId = System.currentTimeMillis()+""+userId;
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(), 0,userId);

        orderDao.saveOrder(order);

        //遍历购物车中每一个商品项转换成为订单项保存到数据库
        for(Map.Entry<Integer,CartItem> entry : cart.getItems().entrySet())
        {
            CartItem value = entry.getValue();
            OrderItem orderItem = new OrderItem(null,value.getName(),value.getCount(),value.getPrice(),value.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);

            BookDao bookDao = new BookImpl();
            // 更新库存和销量
            book book1 = bookDao.queryBookId(value.getId());
            book1.setSales( book1.getSales() + value.getCount() );
            book1.setStock( book1.getStock() - value.getCount() );
            bookDao.update(book1);
        }

        //生成了订单子自动清空购物车
        cart.clearCart();

        return orderId;
    }

    /*----------------------------------查询所有订单（管理员）-----------------------------------------------*/
    @Override
    public List<Order> showAllOrders() {
        List<Order> orders = orderDao.queryOrders();
        return orders;
    }

    /*---------------------------------查看订单详情（管理员和用户）---------------------------------------------------*/
    @Override
    public int showOrderDetail(String orderId) {
        return 0;
    }

    /*-----------------------------------我的订单（用户）------------------------------------------------------*/
    @Override
    public Order showMyOrders(Integer userId) {

        return orderDao.queryOrderByUserId(userId);
    }

    /*--------------------------------------签收订单（用户）---------------------------------------------------*/
    @Override
    public int receiverOrder(String orderId) {
        return 0;
    }

    /*-------------------------------------发货（管理员）-----------------------------------------------------*/
    @Override
    public int sendOrder(String OrderId) {
        return 0;
    }
}
