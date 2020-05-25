package Pro;

import java.util.Date;

public class Book {
    private int book_id;
    private String book_name;
    private String authors;
    private String category;
    private String barcode;
    private String publisher;
    private Float price;
    private Date create_time;
    private Date publish_time;
    private Date update_time;


    public Book(int book_id, String book_name, String authors, String category, String barcode, String publisher, Float price, Date create_time, Date publish_time, Date update_time) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.authors = authors;
        this.category = category;
        this.barcode = barcode;
        this.publisher = publisher;
        this.price = price;
        this.create_time = create_time;
        this.publish_time = publish_time;
        this.update_time = update_time;
    }

    public Book(String book_name, String authors, String category, String barcode, String publisher, Float price, Date create_time, Date publish_time, Date update_time) {
        this.book_name = book_name;
        this.authors = authors;
        this.category = category;
        this.barcode = barcode;
        this.publisher = publisher;
        this.price = price;
        this.create_time = create_time;
        this.publish_time = publish_time;
        this.update_time = update_time;
    }

    public Book(int book_id) {
        this.book_id = book_id;
    }


    public Book(){

    }
    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
