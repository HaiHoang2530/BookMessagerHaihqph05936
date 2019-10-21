package haihqph05936.iris.bookmessagerhaihqph05936.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import haihqph05936.iris.bookmessagerhaihqph05936.R;
import haihqph05936.iris.bookmessagerhaihqph05936.adapter.MyHoaDonChiTiet;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.HoaDonChiTietDao;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.SachDao;
import haihqph05936.iris.bookmessagerhaihqph05936.model.HoaDon;
import haihqph05936.iris.bookmessagerhaihqph05936.model.HoaDonChiTiet;
import haihqph05936.iris.bookmessagerhaihqph05936.model.Sach;

public class HoaDonchitietActivity extends AppCompatActivity {
    EditText edMaSach, edMaHoaDon, edSoLuong;
    TextView tvThanhTien;
    HoaDonChiTietDao hoaDonChiTietDAO;
    Spinner spnmaSach, spnTensach;
    SachDao sachDAO;
    public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    MyHoaDonChiTiet adapter;
    double thanhTien = 0;
    String masach = "";
    String Tensach = "";
    public List<Sach> saches = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_donchitiet);
        edMaSach = (EditText) findViewById(R.id.edMaSach);
        edMaHoaDon = (EditText) findViewById(R.id.edMaHoaDon);
        edSoLuong = (EditText) findViewById(R.id.edSoLuongMua);
        lvCart = (ListView) findViewById(R.id.lvCart);
        tvThanhTien = (TextView) findViewById(R.id.tvThanhTien);
        spnTensach = findViewById(R.id.spnTenSach);
        adapter = new MyHoaDonChiTiet(this, dsHDCT);
        lvCart.setAdapter(adapter);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if ( b != null ) {
            edMaHoaDon.setText(b.getString("MAHOADON"));
        }
        spnTensach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });

    }

    public void ADDHoaDonCHITIET(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDao(HoaDonchitietActivity.this);
        sachDAO = new SachDao(HoaDonchitietActivity.this);
        try {
            if ( validation() < 0 ) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                Sach sach = sachDAO.getSachByID(edMaSach.getText().toString());
                if ( sach != null ) {
                    int pos = checkMaSach(dsHDCT, edMaSach.getText().toString());
                    HoaDon hoaDon = new HoaDon(edMaHoaDon.getText().toString(), new Date());
                    HoaDonChiTiet hoaDonChiTiet = new
                            HoaDonChiTiet(1, hoaDon, sach, Integer.parseInt(edSoLuong.getText().toString()));
                    if ( pos >= 0 ) {
                        int soluong = dsHDCT.get(pos).getSoLuongMua();
                        hoaDonChiTiet.setSoLuongMua(soluong +
                                Integer.parseInt(edSoLuong.getText().toString()));
                        dsHDCT.set(pos, hoaDonChiTiet);
                    } else {
                        dsHDCT.add(hoaDonChiTiet);
                    }
                    adapter.changeDataset(dsHDCT);
                } else {
                    Toast.makeText(getApplicationContext(), "Mã sách không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public void thanhToanHoaDon(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDao(HoaDonchitietActivity.this);
        //tinh tien
        thanhTien = 0;
        try {
            for (HoaDonChiTiet hd : dsHDCT) {
                hoaDonChiTietDAO.inserHoaDonChiTiet(hd);
                thanhTien = thanhTien + hd.getSoLuongMua() *
                        hd.getSach().getGiaBia();
            }
            tvThanhTien.setText("Tổng tiền: " + thanhTien);
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public int checkMaSach(List<HoaDonChiTiet> lsHD, String maSach) {
        int pos = -1;
        for (int i = 0; i < lsHD.size(); i++) {
            HoaDonChiTiet hd = lsHD.get(i);
            if ( hd.getSach().getMaSach().equalsIgnoreCase(maSach) ) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public int validation() {
        if
        ( edMaSach.getText().toString().isEmpty() || edSoLuong.getText().toString().isEmpty() ||
                edMaHoaDon.getText().toString().isEmpty() ) {
            return -1;
        }
        return 1;
    }

    public void shohoadon(View view) {
        Intent intent = new Intent(HoaDonchitietActivity.this, QuanLyHoaDonActivity.class);
        startActivity(intent);
    }
}
