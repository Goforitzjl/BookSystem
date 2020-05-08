package Pro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class UsersDao {

    private DBCbyc3p0 dbc;

    public UsersDao() {
        dbc = DBCbyc3p0.getInstance();
    }

    public int addUser(User user) {
        int row_count = -1;
        ResultSet rs = null;
        String sql = "INSERT INTO USERS(UNAME,AGE,GENDER,PASSWORD,TEL,ADDRESS,ISSUPERUSER) "
                + "VALUES(?,?,?,?,?,?,?)";

        try (Connection con = dbc.getConnection();  //自动关闭资源
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setString(1, user.getName());
            psmt.setInt(2, user.getAge());
            psmt.setString(3, user.getGender());
            psmt.setString(4, user.getPassword());
            psmt.setString(5, user.getTel());
            psmt.setString(6, user.getAddress());
            psmt.setBoolean(7, user.getIs_superuser());
            row_count = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }


    public boolean checkUser(User user) {
        boolean flag=false;
        ResultSet rs = null;
        String sql = "SELECT * FROM USERS WHERE UNAME=? AND PASSWORD=?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql)) {
            psmt.setString(1, user.getName());
            psmt.setString(2, user.getPassword());
            rs = psmt.executeQuery();
            flag=rs.next();
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public Vector<User> UserList() {
        Vector<User> list = new Vector<User>();
        ResultSet rs = null;
        String sql = "SELECT * FROM USERS";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            rs = psmt.executeQuery();
            while (rs.next()) {
                int uid = rs.getInt(1);
                String uname = rs.getString(2);
                String password = rs.getString(3);
                int age = rs.getInt(4);
                String gender = rs.getString(5);
                String tel = rs.getString(6);
                String address = rs.getString(7);
                boolean is_super = rs.getBoolean(8);
                User user = new User();
                user.setId(uid);
                user.setName(uname);
                user.setPassword(password);
                user.setAge(age);
                user.setGender(gender);
                user.setTel(tel);
                user.setAddress(address);
                user.setIs_superuser(is_super);
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
