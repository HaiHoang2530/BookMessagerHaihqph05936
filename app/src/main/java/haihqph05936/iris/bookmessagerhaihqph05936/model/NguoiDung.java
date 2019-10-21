package haihqph05936.iris.bookmessagerhaihqph05936.model;

public class NguoiDung {
    private String userName;
    private String password;
    private String phone;
    private String hoten;

    @Override
    public String toString() {
        return "NguoiDung{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", hoten='" + hoten + '\'' +
                '}';
    }

    public NguoiDung() {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.hoten = hoten;
    }

    public NguoiDung(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public NguoiDung(String userName, String password, String phone, String hoten) {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.hoten = hoten;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }
}
