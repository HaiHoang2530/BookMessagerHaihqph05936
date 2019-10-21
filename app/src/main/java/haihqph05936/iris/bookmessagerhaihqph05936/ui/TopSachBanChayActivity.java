package haihqph05936.iris.bookmessagerhaihqph05936.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import haihqph05936.iris.bookmessagerhaihqph05936.R;
import haihqph05936.iris.bookmessagerhaihqph05936.adapter.MyAdapterSach;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.SachDao;
import haihqph05936.iris.bookmessagerhaihqph05936.model.Sach;

public class TopSachBanChayActivity extends AppCompatActivity {

    public static List<Sach> dsSach = new ArrayList<>();
    ListView lvBook;
    MyAdapterSach adapter = null;
    SachDao sachDAO;
    EditText edThang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_sach_ban_chay);
        lvBook = (ListView) findViewById(R.id.lvBookTop);
        edThang = (EditText) findViewById(R.id.edThang);
    }

    public void VIEW_SACH_TOP_10(View view) {
        if ( Integer.parseInt(edThang.getText().toString()) > 13 ||
                Integer.parseInt(edThang.getText().toString()) < 0 ) {
            Toast.makeText(getApplicationContext(), "Không đúng định dạng tháng (1- 12)", Toast.LENGTH_SHORT).show();
        } else {
            sachDAO = new SachDao(TopSachBanChayActivity.this);
            dsSach = sachDAO.getSachTop10(edThang.getText().toString());
            adapter = new MyAdapterSach(this, dsSach);
            lvBook.setAdapter(adapter);
        }

    }
}
