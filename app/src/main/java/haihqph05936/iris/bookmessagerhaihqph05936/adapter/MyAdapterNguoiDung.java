package haihqph05936.iris.bookmessagerhaihqph05936.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import haihqph05936.iris.bookmessagerhaihqph05936.R;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.NguoiDungDao;
import haihqph05936.iris.bookmessagerhaihqph05936.model.NguoiDung;


public class MyAdapterNguoiDung extends BaseAdapter {

    List<NguoiDung> arrNguoiDung;
    public Activity context;
    public LayoutInflater inflater;
    NguoiDungDao nguoiDungDao;

    public MyAdapterNguoiDung(Activity context, List<NguoiDung> arrayNguoiDung) {
        super();
        this.context = context;
        this.arrNguoiDung = arrayNguoiDung;
        this.inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        nguoiDungDao = new NguoiDungDao(context);
    }

    @Override
    public int getCount() {
        return arrNguoiDung.size();
    }

    @Override
    public Object getItem(int i) {
        return arrNguoiDung.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txtName;
        TextView txtPhone;
        ImageView imgDelete;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if ( view == null ) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.item_nguoi_dung, null);
            holder.img = view.findViewById(R.id.imgavata);
            holder.txtName = view.findViewById(R.id.txtName);
            holder.txtPhone = view.findViewById(R.id.txtPhone);
            holder.imgDelete = view.findViewById(R.id.imgdetele);
            //clik vao icon xoa nguoi dung
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nguoiDungDao.deleteNguoiDungByID(arrNguoiDung.get(i).getUserName());
                    arrNguoiDung.remove(i);
                    notifyDataSetChanged();
                }
            });
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();
        NguoiDung _entry = (NguoiDung) arrNguoiDung.get(i);
        if ( i % 3 == 0 ) {
            holder.img.setImageResource(R.mipmap.emone);
        } else if ( i % 3 == 1 ) {
            holder.img.setImageResource(R.mipmap.emone);
        } else {
            holder.img.setImageResource(R.mipmap.emone);
        }
        holder.txtName.setText(_entry.getUserName());
        holder.txtPhone.setText(_entry.getPassword());
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<NguoiDung> items) {
        this.arrNguoiDung = items;
        notifyDataSetChanged();
    }
}
