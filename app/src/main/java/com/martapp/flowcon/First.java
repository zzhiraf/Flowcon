package com.martapp.flowcon;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.martapp.flowcon.db.AppDatabase;
import com.martapp.flowcon.db.Flow;
import com.martapp.flowcon.db.Note;

import java.util.Calendar;
import java.util.Date;

import static java.sql.Types.NULL;


/**
 * A simple {@link Fragment} subclass.
 */
public class First extends Fragment implements View.OnClickListener {
    private String title;
    private int page;
    Button button1p;
    Button button1m;
    Button button2p;
    Button button2m;
    Button button3p;
    Button button3m;
    Button button4p;
    Button button4m;
    Button button5p;
    Button button5m;

    EditText e_potok1;
    EditText e_potok2;
    EditText e_potok3;
    EditText e_potok4;
    EditText e_potok5;
    Date tekday = new Date();
    AppDatabase DB;

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
        DB = AppDatabase.getInstance(getContext());
        //  mToolbar = (Toolbar) getActivity().findViewById(R.id.tabs);
    }

    public Date atStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);


        button1p = (Button) view.findViewById(R.id.button1plus);
        button1p.setOnClickListener(this);
        button1m = (Button) view.findViewById(R.id.button1minus);
        button1m.setOnClickListener(this);

        button2p = (Button) view.findViewById(R.id.button2plus);
        button2p.setOnClickListener(this);
        button2m = (Button) view.findViewById(R.id.button2minus);
        button2m.setOnClickListener(this);

        button3p = (Button) view.findViewById(R.id.button3plus);
        button3p.setOnClickListener(this);
        button3m = (Button) view.findViewById(R.id.button3minus);
        button3m.setOnClickListener(this);

        button4p = (Button) view.findViewById(R.id.button4plus);
        button4p.setOnClickListener(this);
        button4m = (Button) view.findViewById(R.id.button4minus);
        button4m.setOnClickListener(this);

        button5p = (Button) view.findViewById(R.id.button5plus);
        button5p.setOnClickListener(this);
        button5m = (Button) view.findViewById(R.id.button5minus);
        button5m.setOnClickListener(this);

        e_potok1 = (EditText) view.findViewById(R.id.e_potok1);
        e_potok2 = (EditText) view.findViewById(R.id.e_potok2);
        e_potok3 = (EditText) view.findViewById(R.id.e_potok3);
        e_potok4 = (EditText) view.findViewById(R.id.e_potok4);
        e_potok5 = (EditText) view.findViewById(R.id.e_potok5);

        TextView potok1 = view.findViewById(R.id.potok1);
        TextView potok2 = view.findViewById(R.id.potok2);
        TextView potok3 = view.findViewById(R.id.potok3);
        TextView potok4 = view.findViewById(R.id.potok4);
        TextView potok5 = view.findViewById(R.id.potok5);


        Date tekday = new Date();


        e_potok1.setText(getPoint(1));
        e_potok2.setText(getPoint(2));
        e_potok2.setText(getPoint(3));
        e_potok2.setText(getPoint(4));
        e_potok2.setText(getPoint(5));


        potok1.setText(getFlowname(1));
        potok2.setText(getFlowname(2));
        potok3.setText(getFlowname(3));
        potok4.setText(getFlowname(4));
        potok5.setText(getFlowname(5));

        return view;
    }


    public String getPoint(int flowid) {
        Note note = DB.noteDao().getNoteByDateAndID(atStartOfDay(tekday).getTime(), flowid);
        Integer notepoint = 0;
        if (note != null)
            notepoint = note.getPoint();
        return notepoint.toString();
    }

    public String getFlowname(int flowid) {
        Flow flow = DB.flowDao().getflowById(flowid);
        String flowname = "";
        if (flow != null)
            flowname = flow.getFlow_name();

        return flowname;
    }


    public void PressButton(Integer point, Integer numberFlow){
        Note note;
        Boolean insertTrue = false;

        int tekpoint = point;
        e_potok1.setText("" + tekpoint);

        note = DB.noteDao().getNoteByDateAndID(atStartOfDay(tekday).getTime(), numberFlow);
        Integer notepoint = 0;
        if (note == null) {
            insertTrue = true;
            note = new Note();
        }
        note.setDateCreate(atStartOfDay(tekday).getTime());
        note.setFlow_id(numberFlow);
        note.setPoint(tekpoint);
        if (insertTrue)
            DB.noteDao().insert(note);
        else
            DB.noteDao().update(note);
    }

    @Override
    public void onClick(View v) {
        Date tekday = new Date();

        int buttonIndex = v.getId();
        switch (buttonIndex) {
            case R.id.button1minus:
                PressButton(Integer.valueOf(e_potok1.getText().toString()) - 1,1);
                break;
            case R.id.button1plus:

               PressButton(Integer.valueOf(e_potok1.getText().toString()) + 1,1);

                break;
            case R.id.button2minus:
                PressButton(Integer.valueOf(e_potok1.getText().toString()) - 1,2);
                break;
            case R.id.button2plus:
                PressButton(Integer.valueOf(e_potok1.getText().toString()) + 1,2);
                break;
            case R.id.button3minus:
                PressButton(Integer.valueOf(e_potok1.getText().toString()) - 1,3);
                break;
            case R.id.button3plus:
                PressButton(Integer.valueOf(e_potok1.getText().toString()) + 1,3);
                break;
            case R.id.button4minus:
                PressButton(Integer.valueOf(e_potok1.getText().toString()) - 1,4);
                break;
             case R.id.button4plus:
                 PressButton(Integer.valueOf(e_potok1.getText().toString()) + 1,4);
                break;
            case R.id.button5minus:
                PressButton(Integer.valueOf(e_potok1.getText().toString()) - 1,5);
                break;
            case R.id.button5plus:
                PressButton(Integer.valueOf(e_potok1.getText().toString()) + 1,5);
                break;
        }
    }
}
