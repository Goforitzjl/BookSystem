package Pro;

import java.sql.*;
import java.util.Vector;

public class AccountBalanceDao {


    private DBCbyc3p0 dbc;
    private static Pro.AccountBalanceDao db;

    private AccountBalanceDao() {
        dbc = DBCbyc3p0.getInstance();
    }

    public static Pro.AccountBalanceDao getInstance() {
        if (db == null) {
            db = new Pro.AccountBalanceDao();
        }
        return db;
    }

    //增加图书
    public int addAccountBalance(AccountBalance AccountBalance) {
        int row_count = -1;

        String sql = "INSERT INTO account_balance(ACCOUNT_NAME,INIT_AMOUNT,CURRENT_AMOUNT," +
                "AMOUNT,CREATE_TIME) "
                + "VALUES(?,?,?,?,?)";

        try (Connection con = dbc.getConnection();  //自动关闭资源
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setString(1, AccountBalance.getAccount_name());
            psmt.setFloat(2, AccountBalance.getInit_amount());
            psmt.setFloat(3, AccountBalance.getCurrent_amount());
            psmt.setFloat(4, AccountBalance.getAmount());
            psmt.setDate(5, new Date(AccountBalance.getC_t().getTime()));
            row_count = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }


    public Vector<AccountBalance> getAccountBalanceList() {
        Vector<AccountBalance> list = new Vector<AccountBalance>();
        ResultSet rs = null;
        String sql = "SELECT * FROM account_balance";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            rs = psmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                Float init_amount = rs.getFloat(3);
                Float current_amount = rs.getFloat(4);
                Float amount = rs.getFloat(5);
                java.util.Date c_t=rs.getDate(6);
                AccountBalance AccountBalance = new AccountBalance(id, name,init_amount,current_amount,amount,c_t);
                list.add(AccountBalance);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int deleteAccountBalance(AccountBalance AccountBalance) {

        int row_count = 0;

        String sql = "DELETE FROM account_balance WHERE AccountBalance_ID LIKE ?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setInt(1, AccountBalance.getNid());
            row_count = psmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }

    public int updateAccountBalance(AccountBalance AccountBalance) {
        int row_count = 0;
        String sql = "UPDATE account_balance SET ACCOUNT_NAME=?,INIT_AMOUNT=?,CURRENT_AMOUNT=?,AMOUNT=?,CREATE_TIME=? " +
                "WHERE AccountBalance_ID =?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setString(1, AccountBalance.getAccount_name());
            psmt.setFloat(2, AccountBalance.getInit_amount());
            psmt.setFloat(3, AccountBalance.getCurrent_amount());
            psmt.setFloat(4, AccountBalance.getAmount());
            psmt.setDate(5, new Date(AccountBalance.getC_t().getTime()));
            psmt.setInt(6,AccountBalance.getNid());
            row_count = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }

    public AccountBalance getLastvalues(){
        ResultSet rs=null;
        AccountBalance accountBalance=null;
        String sql="SELECT * FROM account_balance WHERE ACCOUNTBALANCE_ID=(" +
                "SELECT MAX(ACCOUNTBALANCE_ID) FROM Account_balance)";
        try(Connection con=dbc.getConnection();
        PreparedStatement psmt=con.prepareStatement(sql);){
            rs=psmt.executeQuery();
            while(rs.next()){
                int id=rs.getInt(1);
                String name=rs.getString(2);
                Float initamount=rs.getFloat(3);
                Float currentamount=rs.getFloat(4);
                Float amount=rs.getFloat(5);
                java.util.Date c_t=rs.getDate(6);
                accountBalance=new AccountBalance(id,name,initamount,currentamount,amount,c_t);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return accountBalance;
    }

}

    
