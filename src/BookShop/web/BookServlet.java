package BookShop.web;

import BookShop.pojo.Page;
import BookShop.pojo.book;
import BookShop.service.BookService;
import BookShop.service.impl.BookServiceImpl;
import BookShop.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    /*----------------------------添加操作-----------------------------------*/
    protected void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo+=1;

        //1、获取请求参数==封装成book对象
        book book1 = WebUtils.CopyparamToBean(req.getParameterMap(),new book());

        //2、调用bookService.addBook()保存图书
        bookService.addBook(book1);

        //3、跳回图书列表页面（/manager/bookServlet?action=list）
        //注意这里已经进行了页面的分页操作（若想）
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    /*---------------------------删除操作--------------------------------------*/

    protected void deletBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数
//        String id = req.getParameter("id");
        //这里获取的id是字符串型，而下面用的deletBook是Integer类型（要进行类型转换）
        //这是一种常见的事情，所以我们把字符串转成Integer变成一个工具写在WebUtils中
//        int i = WebUtils.parseInt(id, 0);
        int i = WebUtils.parseInt(req.getParameter("id"), 0);
        //2、bookservice.deletBook方法删除数据库中的指定id图书
        bookService.deleteBook(i);

        //3、重定向跳转回  模块工程名+/manage/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo") );
    }

    /*---------------------------------修改操作----------------------------------------------*/
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数（这里获取的整个book实例对象所以用WebUtils工具类）
        book book1 = WebUtils.CopyparamToBean(req.getParameterMap(), new book());

        //2、调用方法写入数据库
        bookService.update(book1);

        //3、请求重定向到整个图书列表页面中
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));

    }
    //这个方法是用在   修改图书前获取原来图书的操作
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数id
        int i = WebUtils.parseInt(req.getParameter("id"), 0);
        //2、根据id查询图书信息
        book book = bookService.queryBookId(i);

        //3、保存到request域当中
        req.setAttribute("book",book);

        //4、请求转发到 pages/manager/book_edit.jsp中
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }



    /*----------------------------以列表的方式展示全部图书的操作---------------------------------------------------*/
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1、通过bookService对象获取查询全部图书
        List<book> books = bookService.queryBook();
        //2、把全部图书放进request域当中
        req.setAttribute("books",books);
        //3、通过请求转发到book_manager.jsp页面当中
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }


    /*------------------------------对全部图书进行分页操作------------------------------------------------*/
    public void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求参数pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        //2、用Bookservice.page(pageNo,pageSize)获对象
        Page<book> page = bookService.page(pageNo,pageSize);

        //设置后台的url
        page.setUrl("manager/bookServlet?action=page");

        //3、把page对象保存到request域当中
        req.setAttribute("page",page);

        //4、请求转发到/pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }



}
