package haihqph05936.iris.bookmessagerhaihqph05936.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import haihqph05936.iris.bookmessagerhaihqph05936.R;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.NguoiDungDao;
import haihqph05936.iris.bookmessagerhaihqph05936.model.NguoiDung;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin, btnSigin;
    private CheckBox checkBoxpass;
    private EditText edtUserName, edtPassword;
    private NguoiDungDao nguoiDungDao;
    String strUser, strPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        nguoiDungDao = new NguoiDungDao(LoginActivity.this);
    }

    private void initView() {
        btnLogin = findViewById(R.id.btnLogin);
        btnSigin = findViewById(R.id.btnSigin);
        edtUserName = findViewById(R.id.edtUseName);
        edtPassword = findViewById(R.id.edtPassword);
        checkBoxpass = findViewById(R.id.chkRememberPass);
    }

    public void checkLogin(View view) {


        nguoiDungDao = new NguoiDungDao(this);
        strUser = edtUserName.getText().toString();
        strPass = edtUserName.getText().toString();

        NguoiDung nguoiDung = new NguoiDung(strUser, strPass);
        boolean resualt = nguoiDungDao.isLogin(nguoiDung);
        if ( resualt ) {
            Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
            rememberUser(strUser, strPass, checkBoxpass.isChecked());
            Intent intent
                    = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        } else {
            Toast.makeText(this, "loi lOgin", Toast.LENGTH_LONG).show();
        }
    }

    private void rememberUser(String strUser, String strPass, boolean checked) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if ( !checked ) {
            //xoa tinh trang luu tru truoc do
            edit.clear();
        } else {
            //luu du lieu
            edit.putString("USERNAME", strUser);
            edit.putString("PASSWORD", strPass);
            edit.putBoolean("REMEMBER", checked);
        }
        //luu lai toan bo
        edit.commit();
    }


    public void Singin(View view) {
        Intent intent
                = new Intent(LoginActivity.this, ThemNguoiDungActivity.class);
        startActivity(intent);
    }
}
