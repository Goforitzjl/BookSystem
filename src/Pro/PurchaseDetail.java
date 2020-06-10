package Pro;

public class PurchaseDetail {
    private int nid;
    private int billmainid;
    private int partnerid;
    private int warehouseid;
    private int bookid;
    private int quantity;
    private Float sellprice;
    private Float costprice;
    private Float discountcostprice;
    private Float sellamount;
    private Float costamount;
    private Float discountamount;

    public PurchaseDetail(int nid, int billmainid,int partnerid, int warehouseid, int bookid, int quantity, Float sellprice, Float costprice, Float discountcostprice, Float sellamount, Float costamount, Float discountamount) {
        this.nid = nid;
        this.billmainid = billmainid;
        this.partnerid=partnerid;
        this.warehouseid = warehouseid;
        this.bookid = bookid;
        this.quantity = quantity;
        this.sellprice = sellprice;
        this.costprice = costprice;
        this.discountcostprice = discountcostprice;
        this.sellamount = sellamount;
        this.costamount = costamount;
        this.discountamount = discountamount;
    }

    public PurchaseDetail(int billmainid, int partnerid, int warehouseid, int bookid, int quantity, Float sellprice, Float costprice, Float discountcostprice, Float sellamount, Float costamount, Float discountamount) {
        this.billmainid = billmainid;
        this.partnerid = partnerid;
        this.warehouseid = warehouseid;
        this.bookid = bookid;
        this.quantity = quantity;
        this.sellprice = sellprice;
        this.costprice = costprice;
        this.discountcostprice = discountcostprice;
        this.sellamount = sellamount;
        this.costamount = costamount;
        this.discountamount = discountamount;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public int getBillmainid() {
        return billmainid;
    }

    public void setBillmainid(int billmainid) {
        this.billmainid = billmainid;
    }

    public int getWarehouseid() {
        return warehouseid;
    }

    public void setWarehouseid(int warehouseid) {
        this.warehouseid = warehouseid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Float getSellprice() {
        return sellprice;
    }

    public void setSellprice(Float sellprice) {
        this.sellprice = sellprice;
    }

    public Float getCostprice() {
        return costprice;
    }

    public void setCostprice(Float costprice) {
        this.costprice = costprice;
    }

    public Float getDiscountcostprice() {
        return discountcostprice;
    }

    public void setDiscountcostprice(Float discountcostprice) {
        this.discountcostprice = discountcostprice;
    }

    public Float getSellamount() {
        return sellamount;
    }

    public void setSellamount(Float sellamount) {
        this.sellamount = sellamount;
    }

    public Float getCostamount() {
        return costamount;
    }

    public void setCostamount(Float costamount) {
        this.costamount = costamount;
    }

    public Float getDiscountamount() {
        return discountamount;
    }

    public void setDiscountamount(Float discountamount) {
        this.discountamount = discountamount;
    }

    public int getPartnerid(){
        return this.partnerid;
    }
    public void setPartnerid(int partnerid){
        this.partnerid=partnerid;
    }
}
