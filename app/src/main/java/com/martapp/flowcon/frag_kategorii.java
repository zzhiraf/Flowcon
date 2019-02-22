package com.martapp.flowcon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.martapp.flowcon.db.AppDatabase;
import com.martapp.flowcon.db.Flow;

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
        AppDatabase DB = AppDatabase.getInstance(getContext());
        Flow flow = DB.flowDao().getflowById(1);
        String flowname = "";
        if (flow != null)
            flowname = flow.getFlow_name();


        flow = DB.flowDao().getflowById(2);
        String flowname2 = "";
        if (flow != null)
            flowname2 = flow.getFlow_name();



        TextView potok1 = view.findViewById(R.id.potok1E);
        potok1.setText(flowname);

        TextView potok2 = view.findViewById(R.id.potok2E);
        potok2.setText(flowname2);

        return view;
    }

}
