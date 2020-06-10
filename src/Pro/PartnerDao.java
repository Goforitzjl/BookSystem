package Pro;

import java.sql.*;
import java.util.Vector;

public class PartnerDao {


    private DBCbyc3p0 dbc;
    private static Pro.PartnerDao wd;

    private PartnerDao() {
        dbc = DBCbyc3p0.getInstance();
    }

    public static Pro.PartnerDao getInstance() {
        if (wd == null) {
            wd = new Pro.PartnerDao();
        }
        return wd;
    }


    //增加图书
    public int addPartner(Partner partner) {
        int row_count = -1;

        String sql = "INSERT INTO PARTNER(PARTNER_NAME, PARTNER_TYPE, DISCOUNT, ADDRESS, BANK_NAME, BANK_ACCOUNT, TAX_NUMBER, CONTACT_PERSON, CONTACT_TEL, CREATE_TIME, UPDATE_TIME) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection con = dbc.getConnection();  //自动关闭资源
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setString(1, partner.getName());
            psmt.setString(2, partner.getType());
            psmt.setFloat(3, partner.getDiscount());
            psmt.setString(4, partner.getAddress());
            psmt.setString(5, partner.getBank_name());
            psmt.setString(6, partner.getBank_count());
            psmt.setString(7, partner.getTax_number());
            psmt.setString(8, partner.getContact_person());
            psmt.setString(9, partner.getContact_tel());
            psmt.setDate(10, new Date(partner.getCreate_time().getTime()));
            psmt.setDate(11, new Date(partner.getUpdate_time().getTime()));

            row_count = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }

    public Vector<Partner> getPartnerList(String partnertype) {
        Vector<Partner> list = new Vector<Partner>();
        ResultSet rs = null;
        String sql = "";
        if (partnertype == "所有") {
            sql = "SELECT * FROM Partner ";
        } else {
            sql = "SELECT * FROM Partner WHERE PARTNER_TYPE=" + partnertype;
        }

        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            rs = psmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String type = rs.getString(3);
                Float discount = rs.getFloat(4);
                String address = rs.getString(5);
                String bank_name = rs.getString(6);
                String bank_account = rs.getString(7);
                String tax_number = rs.getString(8);
                String contact_person = rs.getString(9);
                String contact_tel = rs.getString(10);
                java.util.Date c_t = rs.getDate(11);
                java.util.Date u_t = rs.getDate(12);
                Partner Partner = new Partner(id, name, type, discount, address, bank_name, bank_account, tax_number, contact_person, contact_tel, c_t, u_t);
                list.add(Partner);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int deletePartner(Partner Partner) {

        int row_count = 0;

        String sql = "DELETE FROM Partner WHERE Partner_ID LIKE ?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setInt(1, Partner.getId());
            row_count = psmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }

    public int updatePartner(Partner Partner) {
        int row_count = 0;
        String sql = "UPDATE Partner SET PARTNER_NAME=?,PARTNER_TYPE=?,DISCOUNT=?,ADDRESS=?,BANK_NAME=?,BANK_ACCOUNT=?,TAX_NUMBER=?,CONTACT_PERSON=?,CONTACT_TEL=?,UPDATE_TIME=?,CREATE_TIME=? " +
                "WHERE Partner_ID =?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql);) {
            psmt.setString(1, Partner.getName());
            psmt.setString(2, Partner.getType());
            psmt.setFloat(3, Partner.getDiscount());
            psmt.setString(4, Partner.getAddress());
            psmt.setString(5, Partner.getBank_name());
            psmt.setString(6, Partner.getBank_count());
            psmt.setString(7, Partner.getTax_number());
            psmt.setString(8, Partner.getContact_person());
            psmt.setString(9, Partner.getContact_tel());
            psmt.setDate(10, new Date(Partner.getUpdate_time().getTime()));
            psmt.setDate(11, new Date(Partner.getCreate_time().getTime()));
            psmt.setInt(12, Partner.getId());
            row_count = psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row_count;
    }

    public Vector<Partner> selectPartner(String keyword) {
        Vector<Partner> PartnerList = new Vector<Partner>();  //不可以 Vector<Book> bookList=null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Partner WHERE PARTNER_NAME LIKE ? OR TYPE LIKE ? OR CONTACT_PERSON LIKE ?" +
                "Or ADDRESS LIKE ? OR BANK_NAME LIKE ?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql)) {
            psmt.setString(1, "%" + keyword + "%");
            psmt.setString(2, "%" + keyword + "%");
            psmt.setString(3, "%" + keyword + "%");
            psmt.setString(4, "%" + keyword + "%");
            psmt.setString(5, "%" + keyword + "%");

            rs = psmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String type = rs.getString(3);
                Float discount = rs.getFloat(4);
                String address = rs.getString(5);
                String bank_name = rs.getString(6);
                String bank_account = rs.getString(7);
                String tax_number = rs.getString(8);
                String contact_person = rs.getString(9);
                String contact_tel = rs.getString(10);
                java.util.Date c_t = rs.getDate(11);
                java.util.Date u_t = rs.getDate(12);
                Partner Partner = new Partner(id, name, type, discount, address, bank_name, bank_account, tax_number, contact_person, contact_tel, c_t, u_t);
                PartnerList.add(Partner);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return PartnerList;

    }

    public int getIdbyname(String name) {
        int id = -1;
        ResultSet rs = null;
        String sql = "SELECT PARTNER_ID FROM PARTNER WHERE PARTNER_NAME = ?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql)) {
            psmt.setString(1, name);
            rs = psmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public Float getDiscountbyname(String name) {
        Float discount = 0f;
        ResultSet rs = null;
        String sql = "SELECT DISCOUNT FROM PARTNER WHERE PARTNER_NAME = ?";
        try (Connection con = dbc.getConnection();
             PreparedStatement psmt = con.prepareStatement(sql)) {
            psmt.setString(1, name);
            rs = psmt.executeQuery();
            while (rs.next()) {
                discount = rs.getFloat(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return discount;
    }

    public String getNameById(int id){
        String name="";
        ResultSet rs=null;
        String sql="SELECT PARTNER_NAME FROM PARTNER WHERE PARTNER_ID = ?";
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



