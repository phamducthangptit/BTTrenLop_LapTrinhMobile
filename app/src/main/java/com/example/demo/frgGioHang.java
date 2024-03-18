package com.example.demo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class frgGioHang extends Fragment {
    public static List<SanPham> gioHang = new ArrayList<>();
    CustomAdaperSanPham_GioHang adapterSanPham_GH;
    ListView lvGioHang;
    private DatabaseSP databaseSP;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frg_gio_hang, container, false);
        setControl(view);
        setEvent();
        return view;
    }

    private void setEvent() {
        databaseSP = new DatabaseSP(getActivity());
        gioHang.clear();
        gioHang.addAll(databaseSP.DocDLTrongGH());
        adapterSanPham_GH = new CustomAdaperSanPham_GioHang(getContext(), R.layout.layout_item_gh, gioHang, databaseSP);
        lvGioHang.setAdapter(adapterSanPham_GH);
    }

    private void setControl(View view) {
        lvGioHang = view.findViewById(R.id.lvGioHang);
    }
}