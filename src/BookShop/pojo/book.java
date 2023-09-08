package BookShop.pojo;

import org.omg.PortableInterceptor.INACTIVE;

import java.math.BigDecimal;

public class book {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer sales;
    private String author;
    private Integer stock;
    private String imgpath = "static/img/default.jpg";

    public book() {
    }

    public book(Integer id, String name, BigDecimal price, Integer sales, String author, Integer stock, String imgpath) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.sales = sales;
        this.author = author;
        this.stock = stock;
        if(imgpath!=null || "".equals(imgpath))
        {
            this.imgpath = imgpath;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        if(imgpath!=null || "".equals(imgpath))
        {
            this.imgpath = imgpath;
        }
    }

    @Override
    public String toString() {
        return "book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", author='" + author + '\'' +
                ", stock=" + stock +
                ", imgpath='" + imgpath + '\'' +
                '}';
    }
}
