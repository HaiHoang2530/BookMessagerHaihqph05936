package haihqph05936.iris.bookmessagerhaihqph05936.model;

public class HoaDonChiTiet {
    private int maHDCT;
    private HoaDon hoaDon;
    private Sach sach;
    private int soLuongMua;
    private String TenSach;

    public HoaDonChiTiet(String tenSach) {
        TenSach = tenSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" +
                "maHDCT=" + maHDCT +
                ", hoaDon=" + hoaDon +
                ", sach=" + sach +
                ", soLuongMua=" + soLuongMua +
                '}';
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public HoaDonChiTiet(int maHDCT, HoaDon hoaDon, Sach sach, int soLuongMua) {
        this.maHDCT = maHDCT;
        this.hoaDon = hoaDon;
        this.sach = sach;
        this.soLuongMua = soLuongMua;
    }

    public HoaDonChiTiet() {
        this.maHDCT = maHDCT;
        this.hoaDon = hoaDon;
        this.sach = sach;
        this.soLuongMua = soLuongMua;
    }
}
