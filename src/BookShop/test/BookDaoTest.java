package BookShop.test;

import BookShop.dao.BookDao;
import BookShop.dao.impl.BookImpl;
import BookShop.pojo.Page;
import BookShop.pojo.book;
import BookShop.service.BookService;
import BookShop.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookDaoTest {

    private BookDao bookDao = new BookImpl();
    private BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new book(null,"时间简史",new BigDecimal(9999),10,"霍金",10,null));
    }

    @Test
    public void deleteBook() {
        bookDao.deleteBook(21);
    }

    @Test
    public void update() {
        bookDao.update(new book(null,"时间简史",new BigDecimal(9999),10,"霍元甲",10,null));
    }

    @Test
    public void queryBookId() {
        System.out.println(bookDao.queryForpageTotalCount());
    }

    @Test
    public void queryBook() {
        for (book item : bookDao.queryBook()) {
            System.out.println(item);
        }
    }

    @Test
    public void page() {
        System.out.println(bookService.page(1,Page.PAGE_SIZE));
    }

    @Test
    public void queryForpageTotalCountByPrice()
    {
        System.out.println(bookDao.queryForpageTotalCountByPrice(10,100));
    }

    @Test
    public void queryForPageItemsByPrice()
    {
        System.out.println(bookDao.queryForPageItemsByPrice(0,4,10,50));
    }




}