package haihqph05936.iris.bookmessagerhaihqph05936.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import haihqph05936.iris.bookmessagerhaihqph05936.dao.HoaDonChiTietDao;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.HoaDonDao;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.NguoiDungDao;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.SachDao;
import haihqph05936.iris.bookmessagerhaihqph05936.dao.TheLoaiDao;
import haihqph05936.iris.bookmessagerhaihqph05936.model.HoaDon;

public class DatabaseHelper extends SQLiteOpenHelper {
    //khai bao ten DB
    public static final String DB_QUAN_LY_SACH = "Quan_Ly_Sach";
    public static final int VERSION_1 = 7;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_QUAN_LY_SACH, null, VERSION_1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(NguoiDungDao.SQL_NGUOI_DUNG);
        sqLiteDatabase.execSQL(TheLoaiDao.SQL_THE_LOAI);
        sqLiteDatabase.execSQL(SachDao.SQL_SACH);
        sqLiteDatabase.execSQL(HoaDonDao.SQL_HOA_DON);
        sqLiteDatabase.execSQL(HoaDonChiTietDao.SQL_HOA_DON_CHI_TIET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + NguoiDungDao.TABLE_NAME);
        sqLiteDatabase.execSQL("Drop table if exists " + TheLoaiDao.TABLE_NAME);
        sqLiteDatabase.execSQL("Drop table if exists " + SachDao.TABLE_NAME);
        sqLiteDatabase.execSQL("Drop table if exists " + HoaDonDao.TABLE_NAME);
        sqLiteDatabase.execSQL("Drop table if exists " + HoaDonChiTietDao.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    //khai bao TB_NGUOI_DUNG

}
