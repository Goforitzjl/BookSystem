package Pro;

import java.sql.*;
import java.util.Vector;

public class WareHouseDao {


    private DBCbyc3p0 dbc;
    private static Pro.WareHouseDao wd;

    private WareHouseDao() {
        dbc = DBCbyc3p0.getInstance();
    }

    public static Pro.WareHouseDao getInstance() {
        if (wd == null) {
            wd = new Pro.WareHouseDao();
        }
        return wd;
    }


    //增加图书
    public int addWareHouse(WareHouse wareHouse) {
        int row_count = -1;

        String sql = "INSERT INTO WAREHOUSE(WARENAME,ADDRESS,CREATE_TIME,UPDATE_TIME) "
                + "VALUES(?,?,?,?)";

        try (Connection con = dbc.getConnection();  //自动关闭资源
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setString(1, wareHouse.getWarename());
            psmt.setString(2, wareHouse.getAddress());
            psmt.setDate(3, new Date(wareHouse.getCreatetime().getTime()));
            psmt.setDate(4, new Date(wareHouse.getUpdatetime().getTime()));
            row_count = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }


    public Vector<WareHouse> getWareHouseList() {
        Vector<WareHouse> list = new Vector<WareHouse>();
        ResultSet rs = null;
        String sql = "SELECT * FROM warehouse";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            rs = psmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String address = rs.getString(3);
                java.util.Date c_t = rs.getDate(4);
                java.util.Date u_t = rs.getDate(5);
                WareHouse wareHouse = new WareHouse(id, name, address, c_t, u_t);
                list.add(wareHouse);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int deleteWareHouse(WareHouse wareHouse) {

        int row_count = 0;

        String sql = "DELETE FROM WAREHOUSE WHERE WAREHOUSE_ID LIKE ?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setInt(1, wareHouse.getWarehouseid());
            row_count = psmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }

    public int updateWareHouse(WareHouse wareHouse) {
        int row_count = 0;
        String sql = "UPDATE WAREHOUSE SET WARENAME=?,ADDRESS=?,CREATE_TIME=?,UPDATE_TIME=? " +
                "WHERE WAREHOUSE_ID =?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setString(1, wareHouse.getWarename());
            psmt.setString(2, wareHouse.getAddress());
            psmt.setDate(3, new Date(wareHouse.getCreatetime().getTime()));
            psmt.setDate(4, new Date(wareHouse.getUpdatetime().getTime()));
            psmt.setInt(5, wareHouse.getWarehouseid());
            row_count = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }

    public Vector<WareHouse> selectWareHouse(String keyword) {
        Vector<WareHouse> wareHouseList = new Vector<WareHouse>();  //不可以 Vector<Book> bookList=null;
        ResultSet rs = null;
        String sql = "SELECT * FROM WAREHOUSE WHERE WARENAME LIKE ? OR ADDRESS LIKE ? OR WAREHOUSE_ID LIKE ?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql)) {
            psmt.setString(1, "%" + keyword + "%");
            psmt.setString(2, "%" + keyword + "%");
            psmt.setString(3, "%" + keyword + "%");

            rs = psmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String address = rs.getString(3);
                java.util.Date c_t = rs.getDate(4);
                java.util.Date u_t = rs.getDate(5);
                WareHouse wareHouse = new WareHouse(id, name, address,c_t, u_t);
                wareHouseList.add(wareHouse);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wareHouseList;

    }


    public int getIdbyname(String name){
        int id=-1;
        ResultSet rs=null;
        String sql="SELECT WAREHOUSE_ID FROM warehouse WHERE WARENAME = ?";
        try(Connection con=dbc.getConnection();
            PreparedStatement psmt=con.prepareStatement(sql)){
            psmt.setString(1,name);
            rs=psmt.executeQuery();
            while(rs.next()){
                id=rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return id;
    }

    public String getNameById(int id){
        String name="";
        ResultSet rs=null;
        String sql="SELECT WARENAME FROM warehouse WHERE WAREHOUSE_ID = ?";
        try(Connection con=dbc.getConnection();
            PreparedStatement psmt=con.prepareStatement(sql)){
            psmt.setInt(1,id);
            rs=psmt.executeQuery();
            while(rs.next()){
                name=rs.getString(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return name;
    }

}




