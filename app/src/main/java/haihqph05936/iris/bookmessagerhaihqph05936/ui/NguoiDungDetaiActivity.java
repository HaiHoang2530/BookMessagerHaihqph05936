package haihqph05936.iris.bookmessagerhaihqph05936.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import haihqph05936.iris.bookmessagerhaihqph05936.R;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.NguoiDungDao;

public class NguoiDungDetaiActivity extends AppCompatActivity {
    private EditText edFullName, edPhone;
    private NguoiDungDao nguoiDungDAO;
    private String username, fullname, phone, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung_detai);
        edFullName = (EditText) findViewById(R.id.edFullName);
        edPhone = (EditText) findViewById(R.id.edPhone);
        nguoiDungDAO = new NguoiDungDao(this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        fullname = b.getString("FULLNAME");
        phone = b.getString("PHONE");
        username = b.getString("USERNAME");
        password = b.getString("PASSWORD");
        edFullName.setText(fullname);
        edPhone.setText(phone);
    }

    public void updateUser(View view) {
        if ( nguoiDungDAO.updateInfoNguoiDung(username, password, edPhone.getText().toString(),
                edFullName.getText().toString()) > 0 ) {
            Toast.makeText(this, "Luu Thanh cong!", Toast.LENGTH_SHORT).show();

        }
    }

    public void Huy(View view) {
        finish();
    }
}
