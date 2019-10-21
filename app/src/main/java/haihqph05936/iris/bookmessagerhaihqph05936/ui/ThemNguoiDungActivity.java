package haihqph05936.iris.bookmessagerhaihqph05936.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import haihqph05936.iris.bookmessagerhaihqph05936.R;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.NguoiDungDao;
import haihqph05936.iris.bookmessagerhaihqph05936.database.DatabaseHelper;
import haihqph05936.iris.bookmessagerhaihqph05936.model.NguoiDung;

public class ThemNguoiDungActivity extends AppCompatActivity {
    private EditText edtUser, edtPass, edtPhone, edtFullName, edtRepass;
    private Button btnAdd, btnCancel, btnShow;
    private NguoiDung nguoiDung;
    private DatabaseHelper databaseHelper;
    private NguoiDungDao nguoiDungDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nguoi_dung);
        init();
    }

    public void addThemNguoiDung(View view) {
        nguoiDungDao = new NguoiDungDao(this);
        NguoiDung user = new NguoiDung(edtUser.getText().toString(),
                edtPass.getText().toString(),
                edtPhone.getText().toString(), edtFullName.getText().toString());
        try {
            if ( validateForm() > 0 ) {
                if ( nguoiDungDao.inserNguoiDung(user) > 0 ) {
                    Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Them that bai", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception e) {
            Log.e("error", e.toString());
        }


    }

    private int validateForm() {
        int chech = 1;
        if ( edtUser.getText().length() == 0 || edtPass.getText().length() == 0 ||
                edtPhone.getText().length() == 0 ||
                edtFullName.getText().length() == 0 ) {
            Toast.makeText(this, "Khong duoc de troong", Toast.LENGTH_SHORT).show();
        } else {
            String pass = edtPass.getText().toString();
            String rePass = edtRepass.getText().toString();
            if ( !pass.equals(rePass) ) {
                Toast.makeText(this, "mat khau khong khop", Toast.LENGTH_SHORT).show();
                chech = -1;
            }
        }
        return chech;
    }


    private void init() {
        edtUser = findViewById(R.id.edtUseName);
        edtPass = findViewById(R.id.edtPassword);
        edtPhone = findViewById(R.id.edtPhone);
        edtFullName = findViewById(R.id.edtFullName);
        btnAdd = findViewById(R.id.btnAddName);
        btnCancel = findViewById(R.id.btnCanceUser);
        btnShow = findViewById(R.id.btnShowUser);
        edtRepass = findViewById(R.id.edtRePassword);
    }

    public void showUser(View view) {
        finish();
    }

    public void cancel(View view) {

    }
}
