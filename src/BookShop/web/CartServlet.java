package BookShop.web;

import BookShop.pojo.Cart;
import BookShop.pojo.CartItem;
import BookShop.pojo.book;
import BookShop.service.BookService;
import BookShop.service.impl.BookServiceImpl;
import BookShop.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Watchable;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet{
    private BookService bookService = new BookServiceImpl();


    /*--------------------------购物车中添加图书的操作---------------------------------------------*/
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、设置请求参数（加入一本书是按照它的编号去加入）
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //2、调用bookService方法查询图书信息
        book book = bookService.queryBookId(id);

        //3、把图书信息编程购物车商品栏（CartItem）:这里的做法是调用Cart中的addItem方法将CartItem加到购物车中
        //注意下面这个做法相当于每添加一件商品就换一辆购物车
//        Cart cart = new Cart();
        //因为cart是放在Session中而不是放在数据库中（所以我们可以进行判断：如果没有就创建）
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if(cart == null)
        {
            //创建一个新的购物车
            cart = new Cart();
            //并把它保存到Session中
            req.getSession().setAttribute("cart",cart);

        }

        cart.addItems(new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice()));

        System.out.println(cart);

        //提示用户添加的书名（其实每次添加只要输出购物车中最后一本书的名字就好了）
        req.getSession().setAttribute("lastname",book.getName());

        //4、请求重定向回原来商品所在的页面(这里需要用到Referer来写是因为每件商品来自不同的网页)
        resp.sendRedirect(req.getHeader("Referer"));





    }


    /*-------------------------------------购物车中的删除图书操作----------------------------------------------*/
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数
        int id = WebUtils.parseInt(req.getParameter("id"), 0);


        //3、因为现在是对购物车里的内容进行删除，而购物车中的内容放在session中
        //所以先获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //再通用cart中删除的方法进行删除
        if(cart != null)
        {
            cart.deleteItem(id);
        }


        //3、请求重定向回原来商品所在的页面(这里需要用到Referer来写是因为每件商品来自不同的网页)
        resp.sendRedirect(req.getHeader("Referer"));

    }


    /*-----------------------------------购物车清空操作-----------------------------------------------*/


    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、因为clear方法不用传参数，所以这里不用获取请求参数；直接获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");

        //2、调用clear清除
        if(cart != null)
        {
            cart.clearCart();
        }

        //3、请求重定向
        resp.sendRedirect(req.getHeader("Referer"));
    }


    /*-----------------------------------修改数量操作---------------------------------------------------*/
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);

        Cart cart = (Cart) req.getSession().getAttribute("cart");

        cart.updateCount(id,count);

        resp.sendRedirect(req.getHeader("Referer"));
    }


    /*-----------------------------利用ajax实现商品添加到购物车---------------------------------------------------------*/
    protected void ajaxAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、设置请求参数（加入一本书是按照它的编号去加入）
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //2、调用bookService方法查询图书信息
        book book = bookService.queryBookId(id);

        //3、把图书信息编程购物车商品栏（CartItem）:这里的做法是调用Cart中的addItem方法将CartItem加到购物车中
        //注意下面这个做法相当于每添加一件商品就换一辆购物车
//        Cart cart = new Cart();
        //因为cart是放在Session中而不是放在数据库中（所以我们可以进行判断：如果没有就创建）
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            //创建一个新的购物车
            cart = new Cart();
            //并把它保存到Session中
            req.getSession().setAttribute("cart", cart);

        }

        cart.addItems(new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice()));

        System.out.println(cart);


        CartItem cartItem = new CartItem();
        //提示用户添加的书名（其实每次添加只要输出购物车中最后一本书的名字就好了）
        req.getSession().setAttribute("lastname", book.getName());

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("totalCount",cart.getTotalItem());
        map.put("lastname",cartItem.getName());

        Gson gson = new Gson();
        String s = gson.toJson(map);
        resp.getWriter().write(s);
    }





}
