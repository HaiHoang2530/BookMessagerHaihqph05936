package haihqph05936.iris.bookmessagerhaihqph05936.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import haihqph05936.iris.bookmessagerhaihqph05936.R;
import haihqph05936.iris.bookmessagerhaihqph05936.adapter.MyAdapterTheLoaiSach;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.NguoiDungDao;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.TheLoaiDao;
import haihqph05936.iris.bookmessagerhaihqph05936.database.DatabaseHelper;
import haihqph05936.iris.bookmessagerhaihqph05936.model.NguoiDung;
import haihqph05936.iris.bookmessagerhaihqph05936.model.TheLoai;

public class ThemTheLoaiSachActivity extends AppCompatActivity {

    private EditText edtmaTheLoai, edtNameTheLoai, edtMotaTheLoai;
    private Button btnAddTheLoai, btnCancelTheLoai, btnShowTheLoai;
    private TheLoai theLoai;
    private DatabaseHelper databaseHelper;
    private TheLoaiDao theLoaiDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_the_loai_sach);
        init();
    }

    private void init() {
        edtmaTheLoai = findViewById(R.id.edtMaTheLoai);
        edtNameTheLoai = findViewById(R.id.edtTenTheLoai);
        edtMotaTheLoai = findViewById(R.id.edtMota);
        btnAddTheLoai = findViewById(R.id.btnAddNameSach);
        btnCancelTheLoai = findViewById(R.id.btnCanceBook);
        btnShowTheLoai = findViewById(R.id.btnShowBoook);
    }

    public void AddTheloai(View view) {
        theLoaiDao = new TheLoaiDao(this);
        TheLoai theLoai = new TheLoai(edtmaTheLoai.getText().toString(), edtNameTheLoai.getText().toString(),
                edtMotaTheLoai.getText().toString());
        if ( theLoaiDao.inserTheLoai(theLoai) > 0 ) {
            Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Them that bai", Toast.LENGTH_SHORT).show();
        }

    }
}
