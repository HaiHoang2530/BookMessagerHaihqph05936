package haihqph05936.iris.bookmessagerhaihqph05936.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import haihqph05936.iris.bookmessagerhaihqph05936.R;

public class MainActivity extends AppCompatActivity {
    private ImageView imgNguoiDung, imgSach, imgTheLoai, imgHoaDon, imgTop, imgThongKe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        imgNguoiDung = findViewById(R.id.imgNguoiDug);
        imgSach = findViewById(R.id.imgListBookActivity);
        imgTheLoai = findViewById(R.id.imgTheLoai);
        imgHoaDon = findViewById(R.id.imgListHoaDonActivity);
        imgTop = findViewById(R.id.imgTopSach);
        imgThongKe = findViewById(R.id.imgThongKeActivity);

    }

    public void onClickNguoiDung(View view) {
        Intent intent = new Intent(MainActivity.this, DanhSachNguoiDungActivity.class);
        startActivity(intent);
    }

    public void onClickTheLoai(View view) {
        Intent intent = new Intent(MainActivity.this, DangSachTheLoaiSachActivity.class);
        startActivity(intent);
    }

    public void onClickBook(View view) {
        Intent intent = new Intent(MainActivity.this, QuanLySachActivity.class);
        startActivity(intent);
    }

    public void onClickHoaDon(View view) {
        Intent intent = new Intent(MainActivity.this, QuanLyHoaDonActivity.class);
        startActivity(intent);
    }

    public void onClichkTopSach(View view) {
        Intent intent = new Intent(MainActivity.this, TopSachBanChayActivity.class);
        startActivity(intent);
    }

    public void onClickThongKe(View view) {
        Intent intent = new Intent(MainActivity.this, TongHopThongKeActivity.class);
        startActivity(intent);
    }


}
