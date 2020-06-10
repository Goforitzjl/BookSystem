package Pro;

import java.sql.*;
import java.util.Vector;

public class PurchaseDetailDao {


    private DBCbyc3p0 dbc;
    private static Pro.PurchaseDetailDao wd;

    private PurchaseDetailDao() {
        dbc = DBCbyc3p0.getInstance();
    }

    public static Pro.PurchaseDetailDao getInstance() {
        if (wd == null) {
            wd = new Pro.PurchaseDetailDao();
        }
        return wd;
    }


    //增加图书
    public int addPurchaseDetail(PurchaseDetail PurchaseDetail) {
        int row_count = -1;

        String sql = "INSERT INTO Purchase_detail(BILLMAIN_ID,PARTNER_ID,WAREHOUSE_ID,BOOK_ID,BOOK_QUANTITY,SELL_PRICE,COST_PRICE,DISCOUNT_PRICE,SELL_AMOUNT,COST_AMOUNT,DISCOUNT_AMOUNT) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = dbc.getConnection();  //自动关闭资源
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setInt(1,PurchaseDetail.getBillmainid());
            psmt.setInt(2,PurchaseDetail.getPartnerid());
            psmt.setInt(3,PurchaseDetail.getWarehouseid());
            psmt.setInt(4,PurchaseDetail.getBookid());
            psmt.setInt(5,PurchaseDetail.getQuantity());
            psmt.setFloat(6,PurchaseDetail.getSellprice());
            psmt.setFloat(7,PurchaseDetail.getCostprice());
            psmt.setFloat(8,PurchaseDetail.getDiscountcostprice());
            psmt.setFloat(9,PurchaseDetail.getSellamount());
            psmt.setFloat(10,PurchaseDetail.getCostamount());
            psmt.setFloat(11,PurchaseDetail.getDiscountamount());
            row_count = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }


    public Vector<PurchaseDetail> getPurchaseDetailList() {
        Vector<PurchaseDetail> list = new Vector<PurchaseDetail>();
        ResultSet rs = null;
        String sql = "SELECT * FROM Purchase_detail";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            rs = psmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                int billmainid=rs.getInt(2);
                int partnerid=rs.getInt(3);
                int warehouseid=rs.getInt(4);
                int bookid=rs.getInt(5);
                int quantity=rs.getInt(6);
                Float sellprice=rs.getFloat(7);
                Float costprice=rs.getFloat(8);
                Float discountprice=rs.getFloat(9);
                Float sellamount=rs.getFloat(10);
                Float costamount=rs.getFloat(11);
                Float discountamount=rs.getFloat(12);
                PurchaseDetail PurchaseDetail = new PurchaseDetail(id,billmainid,partnerid,warehouseid,
                        bookid,quantity,sellprice,costprice,discountprice,sellamount,costamount,discountamount);
                list.add(PurchaseDetail);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int deletePurchaseDetail(PurchaseDetail PurchaseDetail) {

        int row_count = 0;

        String sql = "DELETE FROM Purchase_detail WHERE PurchaseDetail_ID LIKE ?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setInt(1, PurchaseDetail.getNid());
            row_count = psmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }

    public int updatePurchaseDetail(PurchaseDetail PurchaseDetail) {
        int row_count = 0;
        String sql = "UPDATE Purchase_detail SET BILLMAIN_ID=?,PARTNER_ID=?,WAREHOUSE_ID=?,BOOK_ID=?,BOOK_QUANTITY=?,SELL_PRICE=?,COST_PRICE=?,DISCOUNT_PRICE=?,SELL_AMOUNT=?,COST_AMOUNT=?,DISCOUNT_AMOUNT=? " +
                "WHERE PurchaseDetail_ID =?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setInt(1,PurchaseDetail.getBillmainid());
            psmt.setInt(2,PurchaseDetail.getPartnerid());
            psmt.setInt(3,PurchaseDetail.getWarehouseid());
            psmt.setInt(4,PurchaseDetail.getBookid());
            psmt.setInt(5,PurchaseDetail.getQuantity());
            psmt.setFloat(6,PurchaseDetail.getSellprice());
            psmt.setFloat(7,PurchaseDetail.getCostprice());
            psmt.setFloat(8,PurchaseDetail.getDiscountcostprice());
            psmt.setFloat(9,PurchaseDetail.getSellamount());
            psmt.setFloat(10,PurchaseDetail.getCostamount());
            psmt.setFloat(11,PurchaseDetail.getDiscountamount());
            psmt.setInt(12,PurchaseDetail.getNid());
            row_count = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }


}



