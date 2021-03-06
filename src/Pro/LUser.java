package Pro;

public class LUser {
    private int id;
    private int age;
    private String name;
    private String password;
    private String gender;
    private String tel;
    private String address;
    private boolean is_superuser;

    public LUser() {
    }

    public LUser(int id, int age, String name, String password, String gender, String tel, String address, boolean is_superuser) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.tel = tel;
        this.address = address;
        this.is_superuser = is_superuser;
    }

    public LUser(int age, String name, String password, String gender, String tel, String address, boolean is_superuser) {
        this.age = age;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.tel = tel;
        this.address = address;
        this.is_superuser = is_superuser;

    }

    public LUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public boolean getIs_superuser() {
        return is_superuser;
    }

    public void setIs_superuser(boolean is_superuser) {
        this.is_superuser = is_superuser;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public LUser(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
