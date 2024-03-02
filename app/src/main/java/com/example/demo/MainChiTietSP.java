package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainChiTietSP extends AppCompatActivity {
    private EditText edtMaSP, edtTenSP, edtGiaSP;
    private Button btnXoa, btnSua, btnBack;
    private DatabaseSP dbSanPham;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chi_tiet_sp);
        Init();
        setControl();
        setEvent();
    }
    public void Init(){
        edtMaSP = findViewById(R.id.edtMaSP);
        edtTenSP = findViewById(R.id.edtTenSP);
        edtGiaSP = findViewById(R.id.edtGiaSP);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnBack = findViewById(R.id.btnBack);
    };
    public void setControl(){
        SanPham sp = (SanPham) getIntent().getSerializableExtra("sp");
        edtTenSP.setText(sp.getTenSP());
        edtMaSP.setText(sp.getMaSP());
        edtGiaSP.setText(sp.getGiaSP());
    }
    public void setEvent(){
        dbSanPham = new DatabaseSP(this);
        SanPham sp = (SanPham) getIntent().getSerializableExtra("sp");
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.setTenSP(edtTenSP.getText().toString());
                sp.setGiaSP(edtGiaSP.getText().toString());
                dbSanPham.SuaDL(sp);
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbSanPham.XoaDL(sp);
                onBackPressed();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}