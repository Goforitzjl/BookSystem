package Pro;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCbyc3p0 {
    private ComboPooledDataSource dataSource;
    private static DBCbyc3p0 dbc;
    private Connection con;

    private DBCbyc3p0() {
        dataSource = new ComboPooledDataSource();
    }

    public static DBCbyc3p0 getInstance() {
        if (dbc == null) {
            dbc = new DBCbyc3p0();
        }
        return dbc;
    }

    public Connection getConnection() {
        try {
            con = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void close(ResultSet rs, Statement stmt, Connection con) {

        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
