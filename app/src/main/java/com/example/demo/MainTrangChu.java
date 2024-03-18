package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainTrangChu extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_trang_chu);
        setControl();
        setEvent();
    }

    private void setEvent() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if(item.getItemId() == R.id.mnDanhSach){
                    fragment = new frgDanhSach();
                    Toast.makeText(MainTrangChu.this, "Danh sach", Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId() == R.id.mnGioHang){
                    fragment = new frgGioHang();
//                    Toast.makeText(MainTrangChu.this, "Gio hang", Toast.LENGTH_SHORT).show();
                }
                if(item.getItemId() == R.id.mnCaiDat){
                    Toast.makeText(MainTrangChu.this, "Cai dat", Toast.LENGTH_SHORT).show();
                }
                drawerLayout.close();
                getSupportFragmentManager().beginTransaction().replace(R.id.flContent, fragment).commit();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    private void setControl() {
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigation);
    }
}