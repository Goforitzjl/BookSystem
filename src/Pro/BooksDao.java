package Pro;

import java.sql.*;
import java.util.Vector;


public class BooksDao {

    private DBCbyc3p0 dbc;
    private static BooksDao db;

    private BooksDao() {
        dbc = DBCbyc3p0.getInstance();
    }

    public static BooksDao getInstance(){
        if(db==null){
            db=new BooksDao();
        }
        return db;
    }



    //增加图书
    public int addBook(Book book) {
        int row_count = -1;

        String sql = "INSERT INTO BOOK(BOOK_NAME,AUTHORS,CATEGORY,BARCODE,PUBLISHER,PRICE,PUBLISH_TIME,CREATE_TIME,UPDATE_TIME) "
                + "VALUES(?,?,?,?,?,?,?,?,?)";

        try (Connection con = dbc.getConnection();  //自动关闭资源
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setString(1, book.getBook_name());
            psmt.setString(2, book.getAuthors());
            psmt.setString(3, book.getCategory());
            psmt.setString(4, book.getBarcode());
            psmt.setString(5, book.getPublisher());
            psmt.setFloat(6, book.getPrice());
            psmt.setDate(7, new Date(book.getPublish_time().getTime()));
            psmt.setDate(8, new Date(book.getCreate_time().getTime()));
            psmt.setDate(9, new Date(book.getUpdate_time().getTime()));
//            psmt.setTimestamp(7,new Timestamp(book.getPublish_time().getTime()));
//            psmt.setTimestamp(8,new Timestamp(book.getCreate_time().getTime()));
//            psmt.setTimestamp(9,new Timestamp(book.getUpdate_time().getTime()));
//            System.out.println(new Timestamp(book.getPublish_time().getTime()));
            row_count = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }


    public Vector<Book> getBookList() {
        Vector<Book> list = new Vector<Book>();
        ResultSet rs = null;
        String sql = "SELECT * FROM Book";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            rs = psmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String authors = rs.getString(3);
                String category = rs.getString(4);
                String barcode = rs.getString(5);
                String publisher = rs.getString(6);
                Float price = rs.getFloat(7);
                java.util.Date p_t = rs.getDate(8);
                java.util.Date c_t = rs.getDate(9);
                java.util.Date u_t = rs.getDate(10);
                Book book = new Book(id, name, authors, category, barcode, publisher, price, p_t, c_t, u_t);
                list.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int deleteBook(Book book) {

        int row_count = 0;

        String sql = "DELETE FROM BOOK WHERE BOOK_ID LIKE ?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setInt(1, book.getBook_id());
            row_count = psmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }

    public int updateBook(Book book) {
        int row_count = 0;
        String sql = "UPDATE BOOK SET BOOK_NAME=?,AUTHORS=?,CATEGORY=?,BARCODE=?," +
                "PUBLISHER=?,PRICE=?,PUBLISH_TIME=?,CREATE_TIME=?,UPDATE_TIME=? " +
                "WHERE BOOK_ID =?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setString(1, book.getBook_name());
            psmt.setString(2, book.getAuthors());
            psmt.setString(3, book.getCategory());
            psmt.setString(4, book.getBarcode());
            psmt.setString(5, book.getPublisher());
            psmt.setFloat(6, book.getPrice());
            psmt.setDate(7, new Date(book.getPublish_time().getTime()));
            psmt.setDate(8, new Date(book.getCreate_time().getTime()));
            psmt.setDate(9, new Date(book.getUpdate_time().getTime()));
            psmt.setInt(10,book.getBook_id());
            row_count=psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }

    public Vector<Book> selectBook(String keyword){
        Vector<Book> bookList=new Vector<Book>();  //不可以 Vector<Book> bookList=null;
        ResultSet rs=null;
        String sql="SELECT * FROM BOOK WHERE BOOK_NAME LIKE ? OR AUTHORS LIKE ? " +
                "OR CATEGORY LIKE ? OR PUBLISHER LIKE ? OR BOOK_ID LIKE ?";
        try(Connection con =dbc.getConnection();
        PreparedStatement psmt=con.prepareStatement(sql)) {
            psmt.setString(1,"%"+keyword+"%");
            psmt.setString(2,"%"+keyword+"%");
            psmt.setString(3,"%"+keyword+"%");
            psmt.setString(4,"%"+keyword+"%");
            psmt.setString(5,"%"+keyword+"%");

            rs = psmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String authors = rs.getString(3);
                String category = rs.getString(4);
                String barcode = rs.getString(5);
                String publisher = rs.getString(6);
                Float price = rs.getFloat(7);
                java.util.Date p_t = rs.getDate(8);
                java.util.Date c_t = rs.getDate(9);
                java.util.Date u_t = rs.getDate(10);
                Book book = new Book(id, name, authors, category, barcode, publisher, price, p_t, c_t, u_t);
                bookList.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;

    }

    public int getIdbyname(String name){
        int id=-1;
        ResultSet rs=null;
        String sql="SELECT BOOK_ID FROM book WHERE BOOK_NAME = ?";
        try(Connection con=dbc.getConnection();
            PreparedStatement psmt=con.prepareStatement(sql)){
            psmt.setString(1,name);
            rs=psmt.executeQuery();
            while(rs.next()){
                id=rs.getInt(1);
            }
        }catch (SQLException e){
            id=-1;
        }
        return id;
    }

    public Book selectBookById(int id){

        Book book=null;
        ResultSet rs=null;
        String sql="SELECT * FROM BOOK WHERE BOOK_ID =?";
        try(Connection con=dbc.getConnection();
        PreparedStatement psmt=con.prepareStatement(sql)){
            psmt.setInt(1,id);
            rs=psmt.executeQuery();
            while(rs.next()){
                String name=rs.getString(2);
                String barcode=rs.getString(5);
                book=new Book(name,barcode);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    return book;
    }


}


