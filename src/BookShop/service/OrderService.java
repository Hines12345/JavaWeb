package BookShop.service;

import BookShop.pojo.Cart;
import BookShop.pojo.Order;
import BookShop.pojo.OrderItem;

import java.util.List;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);

    public List<Order> showAllOrders();

    public int showOrderDetail(String orderId);

    public Order showMyOrders(Integer userId);

    public int receiverOrder (String orderId);

    public int sendOrder(String OrderId);

}
