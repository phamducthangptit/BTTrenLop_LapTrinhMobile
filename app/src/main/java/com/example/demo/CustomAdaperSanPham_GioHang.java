package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdaperSanPham_GioHang extends ArrayAdapter {
    Context context;
    int resource;
    List<SanPham> data;
    private DatabaseSP dbSanPham;

    public CustomAdaperSanPham_GioHang(@NonNull Context context, int resource, List<SanPham> data, DatabaseSP databaseSP) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
        this.dbSanPham = databaseSP;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        ImageView ivHinh = convertView.findViewById(R.id.ivHinh);
        TextView tvTenSP = convertView.findViewById(R.id.tvTenSP);
        Button btnXoa = convertView.findViewById(R.id.btnXoa);
        SanPham sp = data.get(position);
        tvTenSP.setText(sp.getTenSP());
        if(sp.getLoaiSP().equals("SamSung"))
            ivHinh.setImageResource(R.drawable.samsung);
        if(sp.getLoaiSP().equals("Iphone"))
            ivHinh.setImageResource(R.drawable.iphone);
        if(sp.getLoaiSP().equals("Nokia"))
            ivHinh.setImageResource(R.drawable.nokia);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbSanPham.XoaDLTrongGH(sp);
                frgGioHang.gioHang.remove(sp);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
