package haihqph05936.iris.bookmessagerhaihqph05936.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import haihqph05936.iris.bookmessagerhaihqph05936.database.DatabaseHelper;
import haihqph05936.iris.bookmessagerhaihqph05936.model.TheLoai;

public class TheLoaiDao {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "TheLoai";
    public static final String SQL_THE_LOAI = "CREATE TABLE TheLoai (matheloai TEXT PRIMARY KEY, tentheloai TEXT, mota TEXT);";
    public static final String TAG = "TheLoaiDAO";

    public TheLoaiDao(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public int inserTheLoai(TheLoai theLoai) {
        ContentValues values = new ContentValues();
        values.put("matheloai", theLoai.getMaTheLoai());
        values.put("tentheloai", theLoai.getTenTheLoai());
        values.put("mota", theLoai.getMoTa());

        try {
            if ( db.insert(TABLE_NAME, null, values) == -1 ) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    //getAllTheLoai
    public List<TheLoai> getAllTheLoai() {
        List<TheLoai> theLoais = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null,
                null,
                null,
                null,
                null,
                null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false) {
            TheLoai theLoai = new TheLoai();
            theLoai.setMaTheLoai(cursor.getString(0));
            theLoai.setTenTheLoai(cursor.getString(1));
            theLoai.setMoTa(cursor.getString(2));
            theLoais.add(theLoai);
            Log.d("//=====", theLoai.toString());
            cursor.moveToNext();
        }
        cursor.close();
        return theLoais;
    }

    //update
    public int updateTheLoai(TheLoai theLoai) {
        ContentValues values = new ContentValues();
        values.put("matheloai", theLoai.getMaTheLoai());
        values.put("tentheloai", theLoai.getTenTheLoai());
        values.put("mota", theLoai.getMoTa());

        int result = db.update(TABLE_NAME, values, "matheloai=?", new
                String[]{theLoai.getMaTheLoai()});
        if ( result == 0 ) {
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteTheLoaiByID(String matheloai) {
        int result = db.delete(TABLE_NAME, "matheloai=?", new String[]{matheloai});
        if ( result == 0 )
            return -1;
        return 1;
    }
}
