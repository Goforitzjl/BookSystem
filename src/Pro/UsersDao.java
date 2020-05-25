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


    //增加登录用户
    public int addUser(LUser user) {
        int row_count = -1;
        ResultSet rs = null;
        String sql = "INSERT INTO LUSER(NAME,AGE,GENDER,PASSWORD,TEL,ADDRESS,IS_SUPERUSER) "
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


    //校验登录用户
    public boolean checkUser(LUser user) {
        boolean flag=false;
        ResultSet rs = null;
        String sql = "SELECT * FROM LUSER WHERE NAME=? AND PASSWORD=?";
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

    //打印所有登录用户
    public Vector<LUser> UserList() {
        Vector<LUser> list = new Vector<LUser>();
        ResultSet rs = null;
        String sql = "SELECT * FROM LUSER";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            rs = psmt.executeQuery();
            while (rs.next()) {
                int uid = rs.getInt(1);
                String uname = rs.getString(3);
                String password = rs.getString(4);
                int age = rs.getInt(2);
                String gender = rs.getString(5);
                String tel = rs.getString(6);
                String address = rs.getString(7);
                boolean is_super = rs.getBoolean(8);
                LUser user = new LUser();
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
