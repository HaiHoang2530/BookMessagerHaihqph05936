package haihqph05936.iris.bookmessagerhaihqph05936.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import haihqph05936.iris.bookmessagerhaihqph05936.database.DatabaseHelper;
import haihqph05936.iris.bookmessagerhaihqph05936.model.NguoiDung;

public class NguoiDungDao {
    private SQLiteDatabase database;
    private DatabaseHelper helper;
    public static final String TABLE_NAME = "NguoiDung";
    public static final String SQL_NGUOI_DUNG = "CREATE TABLE NguoiDung(username " +
            "TEXT PRIMARY KEY ,password TEXT ,phone TEXT ,hoten TEXT);";
    public static final String TAG = "HaiHoang";

    public NguoiDungDao(Context context) {
        helper = new DatabaseHelper(context);
        database = helper.getWritableDatabase();
    }


    //insert
    public int inserNguoiDung(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUserName());
        values.put("password", nd.getPassword());
        values.put("phone", nd.getPhone());
        values.put("hoten", nd.getHoten());
        try {
            if ( database.insert(TABLE_NAME, null, values) == -1 ) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    public List<NguoiDung> getAllnguoiDung() {
        List<NguoiDung> dsNguoiDung = new ArrayList<>();
        Cursor c = database.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            NguoiDung ee = new NguoiDung();
            ee.setUserName(c.getString(0));
            ee.setPassword(c.getString(1));
            ee.setPhone(c.getString(2));
            ee.setHoten(c.getString(3));
            dsNguoiDung.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsNguoiDung;
    }

    public int updateNguoiDung(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUserName());
        values.put("password", nd.getPassword());
        values.put("phone", nd.getPhone());
        values.put("hoten", nd.getHoten());
        int result = database.update(TABLE_NAME, values, "username=?", new
                String[]{nd.getUserName()});
        if ( result == 0 ) {
            return -1;
        }
        return 1;
    }

    public int changePasswordNguoiDung(NguoiDung nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUserName());
        values.put("password", nd.getPassword());
        int result = database.update(TABLE_NAME, values, "username=?", new
                String[]{nd.getUserName()});
        if ( result == 0 ) {
            return -1;
        }
        return 1;
    }

    public int updateInfoNguoiDung(String username, String phone, String name, String password) {
        ContentValues values = new ContentValues();
        values.put("useranem", username);
        values.put("password", password);
        values.put("phone", phone);
        values.put("hoten", name);
        int result = database.update(TABLE_NAME, values, "username=?", new
                String[]{username});
        if ( result == 0 ) {
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteNguoiDungByID(String username) {
        int result = database.delete(TABLE_NAME, "username=? ", new String[]{username});
        if ( result == 0 )
            return -1;
        return 1;
    }

    //check login
    public int checkLogin(String username, String password) {
        int result = database.delete(TABLE_NAME, "username=? AND password=?", new
                String[]{username, password});
        if ( result == 0 )
            return -1;
        return 1;
    }

    public boolean isLogin(NguoiDung nguoiDung) {
        String sqlSelete = "SELECT username ,password FROM nguoidung " +
                "where username=? AND password= ?";
        //thuc hien lenh truy van
        Cursor c = database.rawQuery(sqlSelete, new String[]{nguoiDung.getUserName(), nguoiDung.getPassword()});
        if ( c.moveToFirst() ) {
            return true;

        }
        return false;
    }

    //phuong thuc uodate mat khau dung boolean tra true and false
    private boolean isChangePassword(NguoiDung nguoiDung) {
        ContentValues values = new ContentValues();
        values.put("username", nguoiDung.getUserName());
        values.put("password", nguoiDung.getPassword());
        values.put("phone", nguoiDung.getPhone());
        values.put("hoten", nguoiDung.getHoten());
        int result = database.update(TABLE_NAME, values, "username=?", new String[]{nguoiDung.getUserName()});
        if ( result == -1 ) return false;
        return true;
    }
    //
}
