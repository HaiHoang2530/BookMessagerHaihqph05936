package haihqph05936.iris.bookmessagerhaihqph05936.model;

public class Sach {
    private String maSach;
    private String maTheLoai;
    private String tenSach;
    private String tacgia;
    private String NXB;
    private double giaBia;
    private int soLuong;

    public Sach(String maSach, String maTheLoai, String tenSach, String tacgia, String NXB, double giaBia, int soLuong) {
        this.maSach = maSach;
        this.maTheLoai = maTheLoai;
        this.tenSach = tenSach;
        this.tacgia = tacgia;
        this.NXB = NXB;
        this.giaBia = giaBia;
        this.soLuong = soLuong;
    }

    public Sach() {
        this.maSach = maSach;
        this.maTheLoai = maTheLoai;
        this.tenSach = tenSach;
        this.tacgia = tacgia;
        this.NXB = NXB;
        this.giaBia = giaBia;
        this.soLuong = soLuong;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public double getGiaBia() {
        return giaBia;
    }

    public void setGiaBia(double giaBia) {
        this.giaBia = giaBia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "Sach{" +
                "maSach='" + maSach + '\'' +
                ", maTheLoai='" + maTheLoai + '\'' +
                ", tenSach='" + tenSach + '\'' +
                ", tacgia='" + tacgia + '\'' +
                ", NXB='" + NXB + '\'' +
                ", giaBia=" + giaBia +
                ", soLuong=" + soLuong +
                '}';
    }
}
