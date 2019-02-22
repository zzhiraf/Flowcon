package com.martapp.flowcon;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.martapp.flowcon.db.AppDatabase;
import com.martapp.flowcon.db.Flow;

import static java.sql.Types.NULL;


/**
 * A simple {@link Fragment} subclass.
 */
public class First extends Fragment {
    private String  title;
    private int page;
    //Toolbar mToolbar;

    public First() {
        // Required empty public constructor
    }

    public static First newInstance(int page, String title) {
        First fragmentFirst = new First();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
      //  mToolbar = (Toolbar) getActivity().findViewById(R.id.tabs);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        AppDatabase DB = AppDatabase.getInstance(getContext());
        Flow flow = DB.flowDao().getflowById(1);
        String flowname = "";
        if (flow != null)
            flowname = flow.getFlow_name();


        flow = DB.flowDao().getflowById(2);
        String flowname2 = "";
        if (flow != null)
            flowname2 = flow.getFlow_name();



        TextView potok1 = view.findViewById(R.id.potok1);
        potok1.setText(flowname);

        TextView potok2 = view.findViewById(R.id.potok2);
        potok2.setText(flowname2);




        return view;
    }

}
