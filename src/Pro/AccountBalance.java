package Pro;

import java.util.Date;

public class AccountBalance {
    private int nid;
    private String account_name;
    private Float init_amount;
    private Float current_amount;
    private Float amount;
    private Date c_t;

    public AccountBalance(int nid, String account_name, Float init_amount, Float current_amount, Float amount,Date c_t) {
        this.nid = nid;
        this.account_name = account_name;
        this.init_amount = init_amount;
        this.current_amount = current_amount;
        this.amount = amount;
        this.c_t=c_t;
    }

    public AccountBalance(String account_name, Float init_amount, Float current_amount, Float amount,Date c_t) {
        this.account_name = account_name;
        this.init_amount = init_amount;
        this.current_amount = current_amount;
        this.amount = amount;
        this.c_t=c_t;
    }

    public int getNid() {
        return nid;
    }

    public void setNid(int nid) {
        this.nid = nid;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public Float getInit_amount() {
        return init_amount;
    }

    public void setInit_amount(Float init_amount) {
        this.init_amount = init_amount;
    }

    public Float getCurrent_amount() {
        return current_amount;
    }

    public void setCurrent_amount(Float current_amount) {
        this.current_amount = current_amount;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Date getC_t(){
        return c_t;
    }

    public void setC_t(Date c_t){
        this.c_t=c_t;
    }
}
