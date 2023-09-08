package BookShop.service.impl;

import BookShop.dao.BookDao;
import BookShop.dao.impl.BookImpl;
import BookShop.pojo.Page;
import BookShop.pojo.book;
import BookShop.service.BookService;

import java.awt.print.Book;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookImpl();
    @Override
    public void addBook(book book1) {
        bookDao.addBook(book1);
    }

    @Override
    public void deleteBook(Integer id) {
        bookDao.deleteBook(id);
    }

    @Override
    public void update(book book) {
        bookDao.update(book);
    }

    @Override
    public book queryBookId(Integer id) {
        return bookDao.queryBookId(id);
    }

    @Override
    public List<book> queryBook() {
        return bookDao.queryBook();
    }

    @Override
    public Page<book> page(int pageNo, int pageSize) {
        //向Page类传入值需要newPage对象
        Page<book> page = new Page<book>();


        //设置pageSize（每页显示数量）的值
        page.setPageSize(Page.PAGE_SIZE);

        //设置pageTotalCount(总记录数【就是一共有多少本图书】)的值
        //这里求总记录数要在数据库中select count(*) from t_book 即可
        //所以这里又要通过BookDao连接数据库
        Integer pageTotalCount = bookDao.queryForpageTotalCount();
        page.setPageTotalCount(pageTotalCount);

        //设置pageTotal（总页码）
        //这里同样需要求总页码，通过pageTotalCount % pageSize 当除不尽时页面加一
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        //设置items（当前页数据）
        //同样要通过连接数据库 select * from t_book limit begin PageSize;
        int begin = (page.getPageNo() - 1) * pageSize;
        List<book> items = bookDao.queryForPageItems(begin,pageSize);
        page.setItems(items);

        return page;
    }

    @Override
    public Page<book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        //向Page类传入值需要newPage对象
        Page<book> page = new Page<book>();


        //设置pageSize（每页显示数量）的值
        page.setPageSize(Page.PAGE_SIZE);

        //设置pageTotalCount(总记录数【就是一共有多少本图书】)的值
        //这里求总记录数要在数据库中select count(*) from t_book 即可
        //所以这里又要通过BookDao连接数据库
        Integer pageTotalCount = bookDao.queryForpageTotalCountByPrice(min,max);
        page.setPageTotalCount(pageTotalCount);

        //设置pageTotal（总页码）
        //这里同样需要求总页码，通过pageTotalCount % pageSize 当除不尽时页面加一
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) {
            pageTotal+=1;
        }
        page.setPageTotal(pageTotal);

        // 设置当前页码
        page.setPageNo(pageNo);

        //设置items（当前页数据）
        //同样要通过连接数据库 select * from t_book limit begin PageSize;
        int begin = (page.getPageNo() - 1) * pageSize;
        List<book> items = bookDao.queryForPageItemsByPrice(begin,pageSize,min,max);
        page.setItems(items);

        return page;
    }
}
