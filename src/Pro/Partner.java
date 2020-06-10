package Pro;

import java.util.Date;

public class Partner {
    private int id;
    private String name;
    private String type;
    private Float discount;
    private String address;
    private String bank_name;
    private String bank_count;
    private String tax_number;
    private String contact_person;
    private String contact_tel;
    private Date create_time;
    private Date update_time;

    public Partner(int id) {
        this.id = id;
    }

    public Partner(int id, String name, String type, Float discount, String address, String bank_name, String bank_count, String tax_number, String contact_person, String contact_tel, Date create_time, Date update_time) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.discount = discount;
        this.address = address;
        this.bank_name = bank_name;
        this.bank_count = bank_count;
        this.tax_number = tax_number;
        this.contact_person = contact_person;
        this.contact_tel = contact_tel;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public Partner(String name, String type, Float discount, String address, String bank_name, String bank_count, String tax_number, String contact_person, String contact_tel, Date create_time, Date update_time) {
        this.name = name;
        this.type = type;
        this.discount = discount;
        this.address = address;
        this.bank_name = bank_name;
        this.bank_count = bank_count;
        this.tax_number = tax_number;
        this.contact_person = contact_person;
        this.contact_tel = contact_tel;
        this.create_time = create_time;
        this.update_time = update_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_count() {
        return bank_count;
    }

    public void setBank_count(String bank_count) {
        this.bank_count = bank_count;
    }

    public String getTax_number() {
        return tax_number;
    }

    public void setTax_number(String tax_number) {
        this.tax_number = tax_number;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getContact_tel() {
        return contact_tel;
    }

    public void setContact_tel(String contact_tel) {
        this.contact_tel = contact_tel;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
