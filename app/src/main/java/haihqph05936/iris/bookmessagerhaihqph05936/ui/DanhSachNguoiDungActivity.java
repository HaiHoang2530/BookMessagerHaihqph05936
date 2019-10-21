package haihqph05936.iris.bookmessagerhaihqph05936.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import haihqph05936.iris.bookmessagerhaihqph05936.R;
import haihqph05936.iris.bookmessagerhaihqph05936.adapter.MyAdapterNguoiDung;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.NguoiDungDao;
import haihqph05936.iris.bookmessagerhaihqph05936.database.DatabaseHelper;
import haihqph05936.iris.bookmessagerhaihqph05936.model.NguoiDung;

public class DanhSachNguoiDungActivity extends AppCompatActivity {
    public static List<NguoiDung> dsNguoiDung = new ArrayList<>();
    ListView lvNguoiDung;
    MyAdapterNguoiDung adapter = null;
    NguoiDungDao nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_nguoi_dung);
        lvNguoiDung = findViewById(R.id.lvNguoiDung);
        nguoiDungDAO = new NguoiDungDao(DanhSachNguoiDungActivity.this);
        dsNguoiDung = nguoiDungDAO.getAllnguoiDung();
        adapter = new MyAdapterNguoiDung(this, dsNguoiDung);
        lvNguoiDung.setAdapter(adapter);
        lvNguoiDung.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent intent = new
                        Intent(DanhSachNguoiDungActivity.this, NguoiDungDetaiActivity.class);
                Bundle b = new Bundle();
                b.putString("USERNAME", dsNguoiDung.get(position).getUserName());
                b.putString("PHONE", dsNguoiDung.get(position).getPhone());
                b.putString("FULLNAME", dsNguoiDung.get(position).getHoten());
                intent.putExtras(b);
                startActivity(intent);
            }
        });
        lvNguoiDung.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int
                    position, long id) {
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsNguoiDung.clear();
        dsNguoiDung = nguoiDungDAO.getAllnguoiDung();
        adapter.changeDataset(nguoiDungDAO.getAllnguoiDung());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemThem:
                Intent intent = new Intent(DanhSachNguoiDungActivity.this, ThemNguoiDungActivity.class);
                startActivity(intent);
                break;
            case R.id.itemDoiMatKhau:
                Intent intent1 = new Intent(DanhSachNguoiDungActivity.this, DoiMatKhauActivity.class);
                startActivity(intent1);
                break;
            case R.id.itemDangXuat:
                SharedPreferences pref =
                        getSharedPreferences("USER_FILE", MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();
                //xoa tinh trang luu tru truoc do
                edit.clear();
                edit.commit();
                intent = new Intent(DanhSachNguoiDungActivity.this, LoginActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_nguoi_dung, menu);
        return super.onCreateOptionsMenu(menu);
    }


}
