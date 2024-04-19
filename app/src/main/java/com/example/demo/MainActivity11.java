package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity11 extends AppCompatActivity {
    private Button btnThongBao, btnThemSP;
    private EditText edtThongBao, edtTenSanPham, edtGiaSanPham;
    private ListView lvDanhSach;
    private List<SanPham> data = new ArrayList<>();
    CustomAdapterSanPham customAdapterSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        Init();
        setEvent();
    }

    private void setEvent() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        DatabaseReference dataSP = database.getReference("SanPham");
        customAdapterSanPham = new CustomAdapterSanPham(this, R.layout.layout_item_sp, data);
        lvDanhSach.setAdapter(customAdapterSanPham);
        btnThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.setValue(edtThongBao.getText().toString());
            }
        });
        btnThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SanPham sp = new SanPham();
                sp.setMaSP(dataSP.push().getKey());
                sp.setTenSP(edtTenSanPham.getText().toString());
                sp.setGiaSP(edtGiaSanPham.getText().toString());
                sp.setLoaiSP("SamSung");
                dataSP.child(sp.getMaSP()).setValue(sp);
                Toast.makeText(MainActivity11.this, "Them thanh cong", Toast.LENGTH_LONG).show();
            }
        });
        dataSP.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for(DataSnapshot item : snapshot.getChildren()){
                    SanPham sp = new SanPham();
                    sp.setMaSP(item.child("maSP").getValue().toString());
                    sp.setTenSP(item.child("tenSP").getValue().toString());
                    sp.setGiaSP(item.child("giaSP").getValue().toString());
                    sp.setLoaiSP(item.child("loaiSP").getValue().toString());
                    data.add(sp);
                }
                customAdapterSanPham.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Toast.makeText(MainActivity11.this, value, Toast.LENGTH_LONG).show();
                edtThongBao.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    private void Init() {
        btnThongBao = findViewById(R.id.btnThongBao);
        edtThongBao = findViewById(R.id.edtThongBao);
        edtTenSanPham = findViewById(R.id.edtTenSanPham);
        edtGiaSanPham = findViewById(R.id.edtGiaSanPham);
        btnThemSP = findViewById(R.id.btnThemSP);
        lvDanhSach = findViewById(R.id.lvDanhSach);
    }
}