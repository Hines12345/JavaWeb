package BookShop.dao.impl;
import BookShop.pojo.Page;
import BookShop.pojo.book;
import BookShop.dao.BookDao;

import java.awt.print.Book;
import java.util.List;

public class BookImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(book book) {
        String sql = "INSERT INTO t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)  ";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgpath());
    }

    @Override
    public int deleteBook(Integer id) {
        String sql = "delete from t_book where id=?" ;
        return update(sql,id);
    }

    @Override
    public int update(book book) {
        String sql = "update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=?\n" +
                "where id = ?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgpath(),book.getId());

    }

    @Override
    public book queryBookId(Integer id) {
        String sql = "SELECT id,NAME,author,price,sales,stock,img_path FROM t_book where id = ?;";
        return queryForOne(book.class,sql,id);
    }

    @Override
    public List<book> queryBook() {
        String sql = "SELECT * FROM t_book";
        return queryForList(book.class,sql);
    }

    @Override
    public Integer queryForpageTotalCount() {
        String sql = "select count(*) from t_book;";
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();

    }

    @Override
    public List<book> queryForPageItems(int begin,int pageSize) {
        String sql =  "select * from t_book limit ?,?";
        return queryForList(book.class,sql,begin,pageSize);
    }


    @Override
    public Integer queryForpageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number) queryForSingleValue(sql,min,max);
        return count.intValue();
    }

    @Override
    public List<book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select * from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(book.class,sql,min,max,begin,pageSize);
    }
}
