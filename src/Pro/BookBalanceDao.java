package Pro;

import java.sql.*;
import java.util.Vector;

public class BookBalanceDao {


    private DBCbyc3p0 dbc;
    private static Pro.BookBalanceDao db;

    private BookBalanceDao() {
        dbc = DBCbyc3p0.getInstance();
    }

    public static Pro.BookBalanceDao getInstance() {
        if (db == null) {
            db = new Pro.BookBalanceDao();
        }
        return db;
    }


    //增加图书
    public int addBookBalance(BookBalance BookBalance) {
        int row_count = -1;

        String sql = "INSERT INTO book_balance(BOOK_ID,WAREHOUSE_ID,INIT_QUANTITY," +
                "INIT_PRICE,QUANTITY,PRICE) "
                + "VALUES(?,?,?,?,?,?)";

        try (Connection con = dbc.getConnection();  //自动关闭资源
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setInt(1, BookBalance.getBookid());
            psmt.setInt(2, BookBalance.getWarehouseid());
            psmt.setFloat(3, BookBalance.getInit_quantity());
            psmt.setFloat(4, BookBalance.getInit_price());
            psmt.setFloat(5, BookBalance.getQuantity());
            psmt.setFloat(6, BookBalance.getPrice());
            row_count = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }


    public int deleteBookBalance(BookBalance BookBalance) {

        int row_count = 0;

        String sql = "DELETE FROM book_balance WHERE BookBalance_ID LIKE ?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setInt(1, BookBalance.getBookbalanceid());
            row_count = psmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }

    public int updateBookBalance(BookBalance BookBalance) {
        int row_count = 0;
        String sql = "UPDATE BookBalance SET BOOK_ID=?,WAREHOUSE_ID=?,INIT_QUANTITY=?," +
                "INIT_PRICE=?,QUANTITY=?,PRICE=? " +
                "WHERE BookBalance_ID =?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setInt(1, BookBalance.getBookid());
            psmt.setInt(2, BookBalance.getWarehouseid());
            psmt.setFloat(3, BookBalance.getInit_quantity());
            psmt.setFloat(4, BookBalance.getInit_price());
            psmt.setFloat(5, BookBalance.getQuantity());
            psmt.setFloat(6, BookBalance.getPrice());
            psmt.setInt(7, BookBalance.getBookbalanceid());
            row_count = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }

    public BookBalance getLastValuesById(int id){
        BookBalance balance=null;
        ResultSet rs=null;
        String sql="SELECT * FROM book_balance WHERE BOOKBALANCE_ID=(" +
                "SELECT MAX(BOOKBALANCE_ID) FROM BOOK_BALANCE WHERE BOOK_ID = ?)";
        try(Connection con=dbc.getConnection();
        PreparedStatement psmt=con.prepareStatement(sql)){
            psmt.setInt(1,id);
            rs=psmt.executeQuery();
            while(rs.next()){
                int nid=rs.getInt(1);
                int bookid=rs.getInt(2);
                int warehouseid=rs.getInt(3);
                int initquantity=rs.getInt(4);
                Float initprice=rs.getFloat(5);
                int quantity=rs.getInt(6);
                Float price=rs.getFloat(7);
                balance=new BookBalance(nid,bookid,warehouseid,initquantity,initprice,
                        quantity,price);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return balance;
    }





}



