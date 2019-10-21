package haihqph05936.iris.bookmessagerhaihqph05936.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import haihqph05936.iris.bookmessagerhaihqph05936.R;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.HoaDonChiTietDao;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.HoaDonDao;
import haihqph05936.iris.bookmessagerhaihqph05936.model.HoaDon;

public class MyDapterHaoDon extends BaseAdapter {
    List<HoaDon> arrHoaDon;
    List<HoaDon> arrSortHoaDon;
    private Filter hoaDonFilter;
    public Activity context;
    public LayoutInflater inflater;
    HoaDonDao hoadonDAO;
    HoaDonChiTietDao hoaDonChiTietDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    public MyDapterHaoDon(Activity context, List<HoaDon> arrayHoaDon) {
        super();
        this.context = context;
        this.arrHoaDon = arrayHoaDon;
        this.arrSortHoaDon = arrayHoaDon;
        this.inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        hoadonDAO = new HoaDonDao(context);
        hoaDonChiTietDAO = new HoaDonChiTietDao(context);
    }
    @Override
    public int getCount() {
        return  arrHoaDon.size();

    }

    @Override
    public Object getItem(int i) {
        return  arrHoaDon.get(i);
    }
    public static class ViewHolder {
        ImageView img;
        TextView txtMaHoaDon;
        TextView txtNgayMua;
        ImageView imgDelete;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null)
        {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_them_hoa_don, null);
            holder.img = (ImageView) view.findViewById(R.id.ivIcon);
            holder.txtMaHoaDon = (TextView)
                    view.findViewById(R.id.tvMaHoaDon);
            holder.txtNgayMua = (TextView) view.findViewById(R.id.tvNgayMua);
            holder.imgDelete = (ImageView)view.findViewById(R.id.ivDelete);

            view.setTag(holder);
        }
        else
            holder=(ViewHolder)view.getTag();
        HoaDon _entry = (HoaDon) arrHoaDon.get(i);
        holder.img.setImageResource(R.mipmap.hdicon);
        holder.txtMaHoaDon.setText(_entry.getMaHoaDon());
        holder.txtNgayMua.setText(sdf.format(_entry.getNgayMua()));
        return view;

    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(List<HoaDon> items){
        this.arrHoaDon = items;
        notifyDataSetChanged();
    }
    public void resetData() {
        arrHoaDon = arrSortHoaDon;
    }
    public Filter getFilter() {
        if (hoaDonFilter == null)
            hoaDonFilter = new CustomFilter();
        return hoaDonFilter;
    }
    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {
                results.values = arrSortHoaDon;
                results.count = arrSortHoaDon.size();
            }
            else {
                List<HoaDon> lsHoaDon = new ArrayList<HoaDon>();
                for (HoaDon p : arrHoaDon) {
                    if
                    (p.getMaHoaDon().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                        lsHoaDon.add(p);
                }
                results.values = lsHoaDon;
                results.count = lsHoaDon.size();
            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            // Now we have to inform the adapter about the new list filtered
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                arrHoaDon = (List<HoaDon>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
