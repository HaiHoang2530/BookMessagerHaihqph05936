package haihqph05936.iris.bookmessagerhaihqph05936.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import haihqph05936.iris.bookmessagerhaihqph05936.R;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.HoaDonChiTietDao;
import haihqph05936.iris.bookmessagerhaihqph05936.model.HoaDonChiTiet;

public class MyHoaDonChiTiet extends BaseAdapter {
    List<HoaDonChiTiet> arrHoaDonChiTiet;
    public Activity context;
    public LayoutInflater inflater;
    HoaDonChiTietDao hoaDonChiTietDAO;

    public MyHoaDonChiTiet(Activity context, List<HoaDonChiTiet> arrayHoaDonChiTiet) {
        super();
        this.context = context;
        this.arrHoaDonChiTiet = arrayHoaDonChiTiet;
        this.inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoaDonChiTietDAO = new HoaDonChiTietDao(context);
    }

    @Override
    public int getCount() {
        return arrHoaDonChiTiet.size();
    }

    @Override
    public Object getItem(int i) {
        return arrHoaDonChiTiet.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder {
        TextView txtMaSach;
        TextView txtSoLuong;
        TextView txtGiaBia;
        TextView txtThanhTien;
        TextView txtTensch;
        ImageView imgDelete;
        Spinner spnTensach;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if ( view == null ) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_hoa_don_chi_tiet, null);
            holder.txtMaSach = (TextView) view.findViewById(R.id.tvMaSach);
            holder.txtSoLuong = (TextView) view.findViewById(R.id.tvSoLuong);
            holder.txtGiaBia = (TextView) view.findViewById(R.id.tvGiaBia);
            holder.txtThanhTien = (TextView)
                    view.findViewById(R.id.tvThanhTien);
            holder.imgDelete = (ImageView) view.findViewById(R.id.ivDelete);

            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    hoaDonChiTietDAO.deleteHoaDonChiTietByID(String.valueOf(arrHoaDonChiTiet.get(i).getMaHDCT()));
                    arrHoaDonChiTiet.remove(i);
                    notifyDataSetChanged();
                }
            });
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();
        HoaDonChiTiet _entry = (HoaDonChiTiet) arrHoaDonChiTiet.get(i);
        holder.txtMaSach.setText("Mã sách: " + _entry.getSach().getMaSach());
        holder.txtSoLuong.setText("Số lượng: " + _entry.getSoLuongMua());
        holder.txtGiaBia.setText("Giá bìa: " + _entry.getSach().getGiaBia() + " vnd");
        holder.txtThanhTien.setText("Thành tiền: " + _entry.getSoLuongMua() * _entry.getSach().getGiaBia() + " vnd");

        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<HoaDonChiTiet> items) {
        this.arrHoaDonChiTiet = items;
        notifyDataSetChanged();
    }
}
