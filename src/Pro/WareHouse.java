package Pro;

import java.util.Date;

public class WareHouse {
    private int warehouseid;
    private String warename;
    private String address;
    private Date createtime;
    private Date updatetime;

    public WareHouse(int warehouseid, String warename, String address, Date createtime, Date updatetime) {
        this.warehouseid = warehouseid;
        this.warename = warename;
        this.address = address;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public WareHouse(int warehouseid) {
        this.warehouseid = warehouseid;
    }

    public WareHouse(String warename, String address, Date createtime, Date updatetime) {
        this.warename = warename;
        this.address = address;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public int getWarehouseid() {
        return warehouseid;
    }


    public void setWarehouseid(int warehouseid) {
        this.warehouseid = warehouseid;
    }

    public String getWarename() {
        return warename;
    }

    public void setWarename(String warename) {
        this.warename = warename;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}
