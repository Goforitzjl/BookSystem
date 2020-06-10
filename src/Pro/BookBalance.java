package Pro;

public class BookBalance {
    private int bookbalanceid;
    private int bookid;
    private int warehouseid;
    private int init_quantity;
    private Float init_price;

    private int quantity;
    private Float price;


    public BookBalance(int bookbalanceid, int bookid, int warehouseid, int init_quantity, Float init_price,  int quantity, Float price) {
        this.bookbalanceid = bookbalanceid;
        this.bookid = bookid;
        this.warehouseid = warehouseid;
        this.init_quantity = init_quantity;
        this.init_price = init_price;
;
        this.quantity = quantity;
        this.price = price;

    }

    public BookBalance(int bookid, int warehouseid, int init_quantity, Float init_price, int quantity, Float price) {
        this.bookid = bookid;
        this.warehouseid = warehouseid;
        this.init_quantity = init_quantity;
        this.init_price = init_price;

        this.quantity = quantity;
        this.price = price;

    }

    public int getBookbalanceid() {
        return bookbalanceid;
    }

    public void setBookbalanceid(int bookbalanceid) {
        this.bookbalanceid = bookbalanceid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getWarehouseid() {
        return warehouseid;
    }

    public void setWarehouseid(int warehouseid) {
        this.warehouseid = warehouseid;
    }

    public int getInit_quantity() {
        return init_quantity;
    }

    public void setInit_quantity(int init_quantity) {
        this.init_quantity = init_quantity;
    }

    public Float getInit_price() {
        return init_price;
    }

    public void setInit_price(Float init_price) {
        this.init_price = init_price;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

}
