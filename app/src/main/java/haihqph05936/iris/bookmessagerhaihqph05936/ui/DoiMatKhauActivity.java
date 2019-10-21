package haihqph05936.iris.bookmessagerhaihqph05936.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import haihqph05936.iris.bookmessagerhaihqph05936.R;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.NguoiDungDao;
import haihqph05936.iris.bookmessagerhaihqph05936.model.NguoiDung;

public class DoiMatKhauActivity extends AppCompatActivity {
    private EditText edtPassword, edtRePassword;
    private NguoiDungDao nguoiDungDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        edtPassword = findViewById(R.id.edtPassword);
        edtRePassword = findViewById(R.id.edtRePassword);

    }

    public void doimatkhau(View view) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String strUserName = pref.getString("USERNAME", "");
        nguoiDungDao = new NguoiDungDao(DoiMatKhauActivity.this);
        NguoiDung user = new NguoiDung(strUserName, edtPassword.getText().toString(), "",
                "");
        try {
            if ( validateForm() > 0 ) {
                if ( nguoiDungDao.changePasswordNguoiDung(user) > 0 ) {
                    Toast.makeText(getApplicationContext(), "Lưu thành công",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Lưu thất bại",
                            Toast.LENGTH_SHORT).show();
                }
            }
            finish();
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }


    public void cancleMatkhau(View view) {
    }

    public int validateForm() {
        int check = 1;
        if ( edtPassword.getText().length() == 0 || edtRePassword.getText().length() == 0 ) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông ",
                    Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edtPassword.getText().toString();
            String rePass = edtRePassword.getText().toString();
            if ( !pass.equals(rePass) ) {
                Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp",
                        Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}
