package haihqph05936.iris.bookmessagerhaihqph05936.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import haihqph05936.iris.bookmessagerhaihqph05936.R;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.SachDao;
import haihqph05936.iris.bookmessagerhaihqph05936.model.Sach;

public class MyAdapterSach extends BaseAdapter implements Filterable {
    List<Sach> arrSach;
    List<Sach> arrSortSach;
    private Filter sachFilter;
    public Activity context;
    public LayoutInflater inflater;
    SachDao sachDAO;

    public MyAdapterSach(Activity context, List<Sach> arraySach) {
        super();
        this.context = context;
        this.arrSach = arraySach;
        this.arrSortSach = arraySach;
        this.inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sachDAO = new SachDao(context);
    }

    @Override
    public int getCount() {
        return arrSach.size();
    }

    @Override
    public Object getItem(int i) {
        return arrSach.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder {
        public ImageView img;
        public TextView txtBookName;
        public TextView txtBookPrice;
        public TextView txtSoLuong;
        public ImageView imgDelete;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if ( view == null ) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_them_sach, null);
            holder.img = (ImageView) view.findViewById(R.id.imgavataSach);
            holder.txtBookName = (TextView)
                    view.findViewById(R.id.txtNameSach);
            holder.txtSoLuong = (TextView) view.findViewById(R.id.txtsoluong);
            holder.imgDelete = (ImageView) view.findViewById(R.id.imgdetelesach);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sachDAO.deleteSachByID(arrSach.get(i).getMaSach());
                    arrSach.remove(i);
                    notifyDataSetChanged();
                }
            });
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();
        Sach _entry = (Sach) arrSach.get(i);
        holder.img.setImageResource(R.mipmap.bookicon);
        holder.txtBookName.setText("Mã sách: " + _entry.getMaSach());
        holder.txtSoLuong.setText("Số lượng: " + _entry.getSoLuong());
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<Sach> items) {
        this.arrSach = items;
        notifyDataSetChanged();
    }

    public void resetData() {
        arrSach = arrSortSach;
    }

    public Filter getFilter() {
        if ( sachFilter == null )
            sachFilter = new CustomFilter();
        return sachFilter;
    }

    private class CustomFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            // We implement here the filter logic
            if ( constraint == null || constraint.length() == 0 ) {
                results.values = arrSortSach;
                results.count = arrSortSach.size();
            } else {
                List<Sach> lsSach = new ArrayList<Sach>();
                for (Sach p : arrSach) {
                    if
                    ( p.getMaSach().toUpperCase().startsWith(constraint.toString().toUpperCase()) )
                        lsSach.add(p);
                }
                results.values = lsSach;
                results.count = lsSach.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            if ( results.count == 0 )
                notifyDataSetInvalidated();
            else {
                arrSach = (List<Sach>) results.values;
                notifyDataSetChanged();
            }
        }
    }
}
