package BookShop.service;

import BookShop.pojo.Page;
import BookShop.pojo.book;

import java.util.List;

public interface BookService {
    public void addBook(book book1);

    public void deleteBook(Integer id);

    public void update(book book);

    public  book queryBookId(Integer id);

    public List<book>  queryBook();


    Page<book> page(int pageNo, int pageSize);

    Page<book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
