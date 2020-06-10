package Pro;

import java.util.Date;

public class BillMain {
    private int nid;
    private int partnerid;
    private int inwarehouseid;
    private int outwarehouseid;
    private String billtype;
    private int recordscount;
    private Float billsellamount;
    private Float billamount;
    private Float cash;
    private Float bank;
    private String billstatus;
    private Date ct;
    private Date ut;
    private Date at;

    public BillMain(int nid, int partnerid, int inwarehouseid, int outwarehouseid, String billtype, int recordscount, Float billsellamount, Float billamount, Float cash, Float bank, String billstatus, Date ct, Date ut, Date at) {
        this.nid=nid;
        this.partnerid = partnerid;
        this.inwarehouseid = inwarehouseid;
        this.outwarehouseid = outwarehouseid;
        this.billtype = billtype;
        this.recordscount = recordscount;
        this.billsellamount = billsellamount;
        this.billamount = billamount;
        this.cash = cash;
        this.bank = bank;
        this.billstatus = billstatus;
        this.ct = ct;
        this.ut = ut;
        this.at = at;
    }

    public BillMain(int partnerid,int inwarehouseid, int outwarehouseid, String billtype, int recordscount, Float billsellamount, Float billamount, Float cash, Float bank, String billstatus, Date ct, Date ut, Date at) {
        this.partnerid = partnerid;
        this.inwarehouseid = inwarehouseid;
        this.outwarehouseid = outwarehouseid;
        this.billtype = billtype;
        this.recordscount = recordscount;
        this.billsellamount = billsellamount;
        this.billamount = billamount;
        this.cash = cash;
        this.bank = bank;
        this.billstatus = billstatus;
        this.ct = ct;
        this.ut = ut;
        this.at = at;
    }

    public int getNid(){
        return nid;
    }

    public void setNid(int nid){
        this.nid=nid;
    }

    public int getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(int partnerid) {
        this.partnerid = partnerid;
    }

    public int getInwarehouseid() {
        return inwarehouseid;
    }

    public void setInwarehouseid(int inwarehouseid) {
        this.inwarehouseid = inwarehouseid;
    }

    public int getOutwarehouseid() {
        return outwarehouseid;
    }

    public void setOutwarehouseid(int outwarehouseid) {
        this.outwarehouseid = outwarehouseid;
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype;
    }

    public int getRecordscount() {
        return recordscount;
    }

    public void setRecordscount(int recordscount) {
        this.recordscount = recordscount;
    }

    public Float getBillsellamount() {
        return billsellamount;
    }

    public void setBillsellamount(Float billsellamount) {
        this.billsellamount = billsellamount;
    }

    public Float getBillamount() {
        return billamount;
    }

    public void setBillamount(Float billamount) {
        this.billamount = billamount;
    }

    public Float getCash() {
        return cash;
    }

    public void setCash(Float cash) {
        this.cash = cash;
    }

    public Float getBank() {
        return bank;
    }

    public void setBank(Float bank) {
        this.bank = bank;
    }

    public String getBillstatus() {
        return billstatus;
    }

    public void setBillstatus(String billstatus) {
        this.billstatus = billstatus;
    }

    public Date getCt() {
        return ct;
    }

    public void setCt(Date ct) {
        this.ct = ct;
    }

    public Date getUt() {
        return ut;
    }

    public void setUt(Date ut) {
        this.ut = ut;
    }

    public Date getAt() {
        return at;
    }

    public void setAt(Date at) {
        this.at = at;
    }
}
