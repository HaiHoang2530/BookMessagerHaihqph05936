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
import haihqph05936.iris.bookmessagerhaihqph05936.dao.TheLoaiDao;
import haihqph05936.iris.bookmessagerhaihqph05936.model.TheLoai;

public class MyAdapterTheLoaiSach extends BaseAdapter {
    private List<TheLoai> arrTheLoai;
    public Activity context;
    public LayoutInflater inflater;
    private TheLoaiDao theLoaiDao;

    public MyAdapterTheLoaiSach(List<TheLoai> arrTheLoai, Activity context
    ) {
        this.arrTheLoai = arrTheLoai;
        this.context = context;
        this.inflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.theLoaiDao = new TheLoaiDao(context);
    }

    @Override
    public int getCount() {
        return arrTheLoai.size();
    }

    @Override
    public Object getItem(int i) {
        return arrTheLoai.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public static class ViewHolder {
        public ImageView img;
        public TextView txtMaTheLoai;
        public TextView txtTenTheLoai;
        public ImageView imgDelete;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if ( view == null ) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item_them_the_loai_sach, null);
            viewHolder.img = view.findViewById(R.id.imgavata_LoaiSach);
            viewHolder.txtMaTheLoai = view.findViewById(R.id.txtNameTheLoai);
            viewHolder.txtTenTheLoai = view.findViewById(R.id.txtNameTheLoai);
            viewHolder.imgDelete = view.findViewById(R.id.imgdetele_TheLoai);
            viewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    theLoaiDao.deleteTheLoaiByID(arrTheLoai.get(i).getMaTheLoai());
                    arrTheLoai.remove(i);
                    notifyDataSetChanged();
                }
            });
            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();
        TheLoai _entry = (TheLoai) arrTheLoai.get(i);
        viewHolder.img.setImageResource(R.mipmap.cateicon);
        viewHolder.txtMaTheLoai.setText(_entry.getMaTheLoai());
        viewHolder.txtTenTheLoai.setText(_entry.getTenTheLoai());
        return view;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void changeDataset(List<TheLoai> items) {
        this.arrTheLoai = items;
        notifyDataSetChanged();
    }
}
