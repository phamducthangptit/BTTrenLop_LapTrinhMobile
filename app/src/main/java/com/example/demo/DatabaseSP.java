package com.example.demo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseSP extends SQLiteOpenHelper {
    public DatabaseSP(@Nullable Context context) {
        super(context, "dbSanPham", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "Create table tbSanPham (masp text, tensp text, giasp text, loaisp text)";
        db.execSQL(sql);
    }

    public void ThemDL(SanPham sp){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Insert into tbSanPham values (?,?,?,?)";
        db.execSQL(sql, new String[]{sp.getMaSP(), sp.getTenSP(), sp.getGiaSP(), sp.getLoaiSP()});
    }

    public List<SanPham> DocDL(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "Select * From tbSanPham";
        List<SanPham> data = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do {
                SanPham sp = new SanPham();
                sp.setMaSP(cursor.getString(0));
                sp.setTenSP(cursor.getString(1));
                sp.setGiaSP(cursor.getString(2));
                sp.setLoaiSP(cursor.getString(3));
                data.add(sp);
            } while (cursor.moveToNext());
        }

        return data;
    }

    public void XoaDL(SanPham sp){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From tbSanPham where masp=?";
        db.execSQL(sql, new String[]{sp.getMaSP()});
    }

    public void SuaDL(SanPham sp){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Update tbSanPham Set tensp=?, giasp=?, loaisp=? where masp=?";
        db.execSQL(sql, new String[]{sp.getTenSP(), sp.getGiaSP(), sp.getLoaiSP(), sp.getMaSP()});
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
