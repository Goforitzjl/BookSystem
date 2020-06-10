package Pro;

import java.util.Date;

public class PurchaseManage {
    private String bookname;
    private String barcode;
    private int quantity;
    private String partner;
    private String warehouse;
    private String status;
    private Float amount;
    private Float discountamount;
    private Date posttime;

    public PurchaseManage(String bookname, String barcode, int quantity, String partner, String warehouse, String status, Float amount, Float discountamount, Date posttime) {
        this.bookname = bookname;
        this.barcode = barcode;
        this.quantity = quantity;
        this.partner = partner;
        this.warehouse = warehouse;
        this.status = status;
        this.amount = amount;
        this.discountamount = discountamount;
        this.posttime = posttime;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getDiscountamount() {
        return discountamount;
    }

    public void setDiscountamount(Float discountamount) {
        this.discountamount = discountamount;
    }

    public Date getPosttime() {
        return posttime;
    }

    public void setPosttime(Date posttime) {
        this.posttime = posttime;
    }
}
