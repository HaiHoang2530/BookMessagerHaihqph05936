package haihqph05936.iris.bookmessagerhaihqph05936.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import haihqph05936.iris.bookmessagerhaihqph05936.R;
import haihqph05936.iris.bookmessagerhaihqph05936.adapter.MyHoaDonChiTiet;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.HoaDonChiTietDao;
import haihqph05936.iris.bookmessagerhaihqph05936.model.HoaDonChiTiet;

public class ListHoaDonChiTietActivity extends AppCompatActivity {
    public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    MyHoaDonChiTiet adapter = null;
    HoaDonChiTietDao hoaDonChiTietDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_hoa_don_chi_tiet);
        lvCart = (ListView) findViewById(R.id.lvHoaDonChiTiet);
        hoaDonChiTietDAO = new HoaDonChiTietDao(ListHoaDonChiTietActivity.this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if ( b != null ) {
            dsHDCT =
                    hoaDonChiTietDAO.getAllHoaDonChiTietByID(b.getString("MAHOADON"));
        }
        adapter = new MyHoaDonChiTiet(this, dsHDCT);
        lvCart.setAdapter(adapter);
    }

}
