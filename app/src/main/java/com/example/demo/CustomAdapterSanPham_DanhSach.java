package com.example.demo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterSanPham_DanhSach extends ArrayAdapter {
    Context context;
    int resource;
    List<SanPham> data;
    private DatabaseSP dbSanPham = new DatabaseSP(CustomAdapterSanPham_DanhSach.this.getContext());;
    public CustomAdapterSanPham_DanhSach(@NonNull Context context, int resource, List<SanPham> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            convertView = LayoutInflater.from(context).inflate(resource, null);
            Button btnGioHang = convertView.findViewById(R.id.btnGioHang);
            ImageView ivHinh = convertView.findViewById(R.id.ivHinh);
            TextView tvTenSP = convertView.findViewById(R.id.tvTenSP);
            SanPham sp = data.get(position);
            tvTenSP.setText(sp.getTenSP());
            if(sp.getLoaiSP().equals("SamSung"))
                ivHinh.setImageResource(R.drawable.samsung);
            if(sp.getLoaiSP().equals("Iphone"))
                ivHinh.setImageResource(R.drawable.iphone);
        if(sp.getLoaiSP().equals("Nokia"))
            ivHinh.setImageResource(R.drawable.nokia);
        btnGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbSanPham.ThemDLVaoGH(sp);
            }
        });
        return convertView;
    }
}
