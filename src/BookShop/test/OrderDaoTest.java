package BookShop.test;

import BookShop.dao.OrderDao;
import BookShop.dao.impl.OrderDaoImpl;
import BookShop.pojo.Order;
import BookShop.pojo.User;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class OrderDaoTest {
    private OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("1234567891",new Date(),new BigDecimal(100),0, 4));
    }

    @Test
    public void queryOrders() {
        List<Order> orders = orderDao.queryOrders();

        for( Order order : orders)
        {
            System.out.println(order);
        }
    }

    @Test
    public void changeOrderStatus() {
        Integer changeOrderStatus = orderDao.changeOrderStatus(1, "123");
        Order order = new Order();
        System.out.println(order.getStatus());
    }

//    @Test
//    public void queryOrderByUserId() {
//        User user = new User("3","admin","admin","gui.com");
//        List<Order> orders = orderDao.queryOrderByUserId(user.getId());
//        System.out.println(orders);
//    }
}