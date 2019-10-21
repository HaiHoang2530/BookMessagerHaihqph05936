package haihqph05936.iris.bookmessagerhaihqph05936.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import haihqph05936.iris.bookmessagerhaihqph05936.R;
import haihqph05936.iris.bookmessagerhaihqph05936.adapter.MyAdapterTheLoaiSach;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.TheLoaiDao;
import haihqph05936.iris.bookmessagerhaihqph05936.model.TheLoai;

public class DangSachTheLoaiSachActivity extends AppCompatActivity {
    public static List<TheLoai> dsTheLoai = new ArrayList<>();
    ListView lvTheLoai;
    MyAdapterTheLoaiSach adapter = null;
    TheLoaiDao theLoaiDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_sach_the_loai_sach);
        lvTheLoai = (ListView) findViewById(R.id.lvTheLoai);
        registerForContextMenu(lvTheLoai);
        theLoaiDAO = new TheLoaiDao(DangSachTheLoaiSachActivity.this);
        dsTheLoai = theLoaiDAO.getAllTheLoai();
        adapter = new MyAdapterTheLoaiSach(dsTheLoai, this);
        lvTheLoai.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        dsTheLoai.clear();
        dsTheLoai = theLoaiDAO.getAllTheLoai();
        adapter.changeDataset(dsTheLoai);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.them_the_loai_sach, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemThemTheLoai:
                Intent intent = new Intent(DangSachTheLoaiSachActivity.this, ThemTheLoaiSachActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
