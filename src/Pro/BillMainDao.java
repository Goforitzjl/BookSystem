package Pro;

import java.sql.*;
import java.util.Vector;

public class BillMainDao {


    private DBCbyc3p0 dbc;
    private static Pro.BillMainDao db;

    private BillMainDao() {
        dbc = DBCbyc3p0.getInstance();
    }

    public static Pro.BillMainDao getInstance() {
        if (db == null) {
            db = new Pro.BillMainDao();
        }
        return db;
    }

    //增加图书
    public int addBillMain(BillMain BillMain) {
        int row_count = -1;

        String sql = "INSERT INTO Bill_Main(PARTNER_ID,INWAREHOUSE_ID,OUTWAREHOUSE_ID,BILL_TYPE,RECORDS_COUNT,BILLSELL_AMOUNT,BILL_AMOUNT,CASH,BANK,BILL_STATE,CREATE_TIME,UPDATE_TIME,ACCOUNT_TIME) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = dbc.getConnection();  //自动关闭资源
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setInt(1, BillMain.getPartnerid());
            psmt.setInt(2, BillMain.getInwarehouseid());
            psmt.setInt(3, BillMain.getOutwarehouseid());
            psmt.setString(4, BillMain.getBilltype());
            psmt.setInt(5, BillMain.getRecordscount());
            psmt.setFloat(6, BillMain.getBillsellamount());
            psmt.setFloat(7, BillMain.getBillamount());
            psmt.setFloat(8, BillMain.getCash());
            psmt.setFloat(9, BillMain.getBank());
            psmt.setString(10, BillMain.getBillstatus());
            psmt.setDate(11, new Date(BillMain.getCt().getTime()));
            psmt.setDate(12, new Date(BillMain.getUt().getTime()));
            psmt.setDate(13, new Date(BillMain.getAt().getTime()));
//            psmt.setDate(12, new Date(BillMain.getUt().getTime()));
//            psmt.setDate(13, new Date(BillMain.getAt().getTime()));
            row_count = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }

    public Vector<BillMain> getBillMainList() {
        Vector<BillMain> list = new Vector<BillMain>();
        ResultSet rs = null;
        String sql = "SELECT * FROM BillMain";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            rs = psmt.executeQuery();
            while (rs.next()) {
                int nid = rs.getInt(1);
                int partnerid = rs.getInt(2);
                int inwarehouseid = rs.getInt(3);
                int outwarehouseid = rs.getInt(4);
                String billtype = rs.getString(5);
                int recordscount = rs.getInt(6);
                Float billsellamount = rs.getFloat(7);
                Float billamount = rs.getFloat(8);
                Float cash = rs.getFloat(9);
                Float bank = rs.getFloat(10);
                String billstatus = rs.getString(11);
                java.util.Date c_t = rs.getDate(12);
                java.util.Date u_t = rs.getDate(13);
                java.util.Date a_t = rs.getDate(114);
                BillMain BillMain = new BillMain(nid, partnerid, inwarehouseid, outwarehouseid,
                        billtype, recordscount, billsellamount, billamount, cash, bank, billstatus, c_t, u_t, a_t);
                list.add(BillMain);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int deleteBillMain(BillMain BillMain) {

        int row_count = 0;

        String sql = "DELETE FROM BillMain WHERE BillMain_ID LIKE ?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setInt(1, BillMain.getNid());
            row_count = psmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }

    public int updateBillMain(BillMain BillMain) {
        int row_count = 0;
        String sql = "UPDATE BillMain SET PARTNER_ID=?,INWAREHOUSE_ID=?,OUTWAREHOUSE_ID=?,BILL_TYPE=?," +
                "RECORDS_COUNT=?,BILLSELL_AMOUNT=?,BILL_AMOUNT=?,CASH=?,BANK=?," +
                "BILL_STATE=?,CREATE_TIME=?,UPDATE_TIME=?,ACCOUNT_TIME=?"+
                "WHERE BillMain_ID =?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setInt(1, BillMain.getPartnerid());
            psmt.setInt(2, BillMain.getInwarehouseid());
            psmt.setInt(3, BillMain.getOutwarehouseid());
            psmt.setString(4, BillMain.getBilltype());
            psmt.setInt(5, BillMain.getRecordscount());
            psmt.setFloat(6, BillMain.getBillamount());
            psmt.setFloat(7, BillMain.getBillamount());
            psmt.setFloat(8, BillMain.getCash());
            psmt.setFloat(9, BillMain.getBank());
            psmt.setString(10, BillMain.getBillstatus());
            psmt.setDate(11, new Date(BillMain.getCt().getTime()));
            psmt.setDate(12, new Date(BillMain.getUt().getTime()));
            psmt.setDate(13, new Date(BillMain.getAt().getTime()));
            psmt.setInt(14, BillMain.getNid());
            row_count = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }

    public int getMaxId(){
        int id=-1;
        ResultSet rs=null;
        String sql="SELECT max(BILLMAIN_ID) FROM bill_main";
        try(Connection con=dbc.getConnection();
            PreparedStatement psmt=con.prepareStatement(sql)){
            rs=psmt.executeQuery();
            while(rs.next()){
                id=rs.getInt(1);
            }
        }catch (SQLException e){
            id=-1;
        }
        return id;
    }

    public String getStatusById(int id){
        String status="";
        ResultSet rs=null;
        String sql="SELECT BILL_STATE FROM PARTNER WHERE BILLMAIN_ID = ?";
        try(Connection con=dbc.getConnection();
            PreparedStatement psmt=con.prepareStatement(sql)){
            psmt.setInt(1,id);
            rs=psmt.executeQuery();
            while(rs.next()){
                status=rs.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return status;
    }

    public Date getPosttimeById(int id){
        Date date=null;
        ResultSet rs=null;
        String sql="SELECT ACCOUNT_TIME FROM PARTNER WHERE BILLMAIN_ID = ?";
        try(Connection con=dbc.getConnection();
            PreparedStatement psmt=con.prepareStatement(sql)){
            psmt.setInt(1,id);
            rs=psmt.executeQuery();
            while(rs.next()){
                date=rs.getDate(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return date;
    }


}


