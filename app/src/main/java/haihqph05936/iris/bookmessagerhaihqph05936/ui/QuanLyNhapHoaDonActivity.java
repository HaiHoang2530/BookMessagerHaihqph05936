package haihqph05936.iris.bookmessagerhaihqph05936.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import haihqph05936.iris.bookmessagerhaihqph05936.R;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.HoaDonDao;
import haihqph05936.iris.bookmessagerhaihqph05936.model.HoaDon;

public class QuanLyNhapHoaDonActivity extends AppCompatActivity {
    EditText edNgayMua, edMaHoaDon;
    HoaDonDao hoaDonDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nhap_hoa_don);
        edNgayMua = (EditText) findViewById(R.id.edtNgayThang);
        edMaHoaDon = (EditText) findViewById(R.id.edtMaHoaDon);

    }


    public void ADDHoaDon(View view) {
        hoaDonDAO = new HoaDonDao(QuanLyNhapHoaDonActivity.this);
        try {
            if ( validation() < 0 ) {
                Toast.makeText(getApplicationContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                HoaDon hoaDon = new
                        HoaDon(edMaHoaDon.getText().toString(), sdf.parse(edNgayMua.getText().toString()));
                if ( hoaDonDAO.inserHoaDon(hoaDon) > 0 ) {
                    Toast.makeText(getApplicationContext(), "Thêm thành công",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new
                            Intent(QuanLyNhapHoaDonActivity.this, HoaDonchitietActivity.class);
                    Bundle b = new Bundle();
                    b.putString("MAHOADON", edMaHoaDon.getText().toString());
                    intent.putExtras(b);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public int validation() {
        if
        ( edMaHoaDon.getText().toString().isEmpty() || edNgayMua.getText().toString().isEmpty()
        ) {
            return -1;
        }
        return 1;
    }

    public void datepicker(View view) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(QuanLyNhapHoaDonActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i, i1, i2);
                SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
                edNgayMua.setText(sdf.format(calendar.getTime()));
            }
        }, year, month, day);
        datePickerDialog.show();

    }
}
