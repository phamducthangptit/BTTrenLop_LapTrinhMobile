package com.example.demo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class frgDanhSach extends Fragment {
    GridView gvDanhSach;
    List<SanPham> data = new ArrayList<>();
    CustomAdapterSanPham_DanhSach customAdapterSanPham_danhSach;
    DatabaseSP databaseSP;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_danh_sach, container, false);
        setControl(view);
        setEvent();
        return view;
    }

    private void setEvent() {
        databaseSP = new DatabaseSP(getActivity());
        data.addAll(databaseSP.DocDL());
        customAdapterSanPham_danhSach = new CustomAdapterSanPham_DanhSach(getContext(), R.layout.layout_item_sp_custom, data);
        gvDanhSach.setAdapter(customAdapterSanPham_danhSach);
    }

    private void setControl(View view) {
        gvDanhSach = view.findViewById(R.id.gvDanhSach);
    }
}