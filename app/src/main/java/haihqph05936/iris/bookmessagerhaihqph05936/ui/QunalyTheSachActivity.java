package haihqph05936.iris.bookmessagerhaihqph05936.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import haihqph05936.iris.bookmessagerhaihqph05936.R;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.SachDao;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.TheLoaiDao;
import haihqph05936.iris.bookmessagerhaihqph05936.model.Sach;
import haihqph05936.iris.bookmessagerhaihqph05936.model.TheLoai;

public class QunalyTheSachActivity extends AppCompatActivity {
    private SachDao sachDAO;
    TheLoaiDao theLoaiDAO;
    Spinner spnTheLoai;
    EditText edMaSach, edTenSach, edNXB, edTacGia, edGiaBia, edSoLuong;
    String maTheLoai = "";
    List<TheLoai> listTheLoai = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qunaly_the_sach);
        init();
        getTheLoai();

        spnTheLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int
                    position, long id) {
                maTheLoai =
                        listTheLoai.get(spnTheLoai.getSelectedItemPosition()).getMaTheLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        //load data into form
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if ( b != null ) {
            edMaSach.setText(b.getString("MASACH"));
            String maTheLoai = b.getString("MATHELOAI");
            edTenSach.setText(b.getString("TENSACH"));
            edNXB.setText(b.getString("NXB"));
            edTacGia.setText(b.getString("TACGIA"));
            edGiaBia.setText(b.getString("GIABIA"));
            edSoLuong.setText(b.getString("SOLUONG"));
            spnTheLoai.setSelection(checkPositionTheLoai(maTheLoai));
        }
    }

    private void init() {
        spnTheLoai = (Spinner) findViewById(R.id.spnTheLoai);
        edMaSach = (EditText) findViewById(R.id.edtMaSach);
        edTenSach = (EditText) findViewById(R.id.edtTenSach);
        edNXB = (EditText) findViewById(R.id.edtNXB);
        edTacGia = (EditText) findViewById(R.id.edtTentacgia);
        edGiaBia = (EditText) findViewById(R.id.edtGia);
        edSoLuong = (EditText) findViewById(R.id.edtSoLuong);

    }

    public void showSpinner(View view) {
        sachDAO = new SachDao(QunalyTheSachActivity.this);
        sachDAO.getAllSach();
    }

    public void getTheLoai() {
        theLoaiDAO = new TheLoaiDao(QunalyTheSachActivity.this);
        listTheLoai = theLoaiDAO.getAllTheLoai();
        ArrayAdapter<TheLoai> dataAdapter = new ArrayAdapter<TheLoai>(this,
                android.R.layout.simple_spinner_item, listTheLoai);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTheLoai.setAdapter(dataAdapter);
    }

    public void addBook(View view) {
        sachDAO = new SachDao(QunalyTheSachActivity.this);
        Sach sach = new
                Sach(edMaSach.getText().toString(), maTheLoai, edTenSach.getText().toString(),
                edTacGia.getText().toString(), edNXB.getText().toString(),

                Double.parseDouble(edGiaBia.getText().toString()), Integer.parseInt(edSoLuong.getText
                ().toString()));
        try {
            if ( sachDAO.inserSach(sach) > 0 ) {
                Toast.makeText(getApplicationContext(), "Thêm thành công",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Thêm thất bại",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public void showBook(View view) {
        finish();
    }

    public int checkPositionTheLoai(String strTheLoai) {
        for (int i = 0; i < listTheLoai.size(); i++) {
            if ( strTheLoai.equals(listTheLoai.get(i).getMaTheLoai()) ) {
                return i;
            }
        }
        return 0;
    }
}
