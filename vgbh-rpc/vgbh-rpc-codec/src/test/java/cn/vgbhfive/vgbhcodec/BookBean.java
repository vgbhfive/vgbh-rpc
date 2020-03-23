package cn.vgbhfive.vgbhcodec;

/**
 * @author Vgbh
 * @date 2020/3/20 22:35
 */
public class BookBean {

    private Long id;

    private String author;

    private Integer price;

    public BookBean() {
    }

    public BookBean(Long id, String author, Integer price) {
        this.id = id;
        this.author = author;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public BookBean setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookBean setAuthor(String author) {
        this.author = author;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public BookBean setPrice(Integer price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "BookBean{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
