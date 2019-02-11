package com.martapp.flowcon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class frag_kategorii extends Fragment {
    private String  title;
    private int page;

    public frag_kategorii() {
        // Required empty public constructor
    }

    public static frag_kategorii newInstance(int page, String title) {
        frag_kategorii fragment = new frag_kategorii();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kategorii, container, false);
        return view;
    }

}
