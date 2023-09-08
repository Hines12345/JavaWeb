package BookShop.web;

import BookShop.pojo.Cart;
import BookShop.pojo.Order;
import BookShop.pojo.User;
import BookShop.service.OrderService;
import BookShop.service.impl.OrderServiceImpl;
import BookShop.utils.JdbcUtils;
import BookShop.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet{
    OrderService orderService = new OrderServiceImpl();

    /*-------------------------------------生成订单----------------------------------------------*/
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }


       Integer userId = user.getId();
        // 调用 orderService.createOrder(Cart,Userid);生成订单

        String orderId = orderService.createOrder(cart, userId);



        // req.setAttribute("orderId", orderId);
        // 请求转发到/pages/cart/checkout.jsp
        // req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req, resp);
        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }

/*----------------------------------------查看我的订单----------------------------------------------------*/
    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("进入showMyOrders");
        User user = (User) req.getSession().getAttribute("user");
//        List<Order> orders = orderService.showMyOrders(userId);

//        req.setAttribute("orders",orders);

//        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);

    }
}


