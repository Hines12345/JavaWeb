package BookShop.test;

import BookShop.pojo.Cart;
import BookShop.pojo.CartItem;
import BookShop.pojo.Order;
import BookShop.pojo.User;
import BookShop.service.OrderService;
import BookShop.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        OrderService orderService = new OrderServiceImpl();
        Cart cart = new Cart();
        cart.addItems(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItems(new CartItem(1, "java从入门到精通", 1, new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItems(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        orderService.createOrder(cart,1);
    }

    @Test
    public void showMyOrders()
    {
        OrderService orderService = new OrderServiceImpl();
        User user = new User(1,"admin","admin","2587688776@qq.com");
        Order order = orderService.showMyOrders(1);
        System.out.println(order);
    }

    @Test
    public void showOrders(){
        OrderService orderService = new OrderServiceImpl();
        List<Order> orders = orderService.showAllOrders();
        orders.forEach(System.out::println);
    }

}