package BookShop.dao;

import BookShop.pojo.book;

import java.awt.print.Book;
import java.util.List;

public interface BookDao {
    //图书管理的操作业务

    //1、添加图书
    public int addBook(book book);

    //2、删除图书
    public int deleteBook(Integer id);

    //3、修改图书
    public int update(book book);

    //4、查询
    public book queryBookId(Integer id);

    public List<book> queryBook();

    Integer queryForpageTotalCount();

    List<book> queryForPageItems(int begin, int pageSize);

    /*--------------------价格查询操作----------------*/

    //按价格区间查询总记录数
    Integer queryForpageTotalCountByPrice(int min, int max);

    //按价格查询显示页的数据
    List<book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}