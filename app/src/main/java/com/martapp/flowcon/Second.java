package com.martapp.flowcon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.numetriclabz.numandroidcharts.ChartData;
import com.numetriclabz.numandroidcharts.RadarChart;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Second extends Fragment{
        private String  title;
        private int page;

    public static Second newInstance(int page, String title) {
        Second fragmentSecond = new Second();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentSecond.setArguments(args);
        return fragmentSecond;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("Second");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);

        RadarChart radarChart = (RadarChart) view.findViewById(R.id.radar_chart);
        List<ChartData> values = new ArrayList();
        ArrayList <String> label = new ArrayList();
        label.add("Communication");
        label.add("Tech Know");
        label.add("Meetings");
        label.add("Team Work");
        label.add("Punctuality");
        label.add("Problem");
        ArrayList <Float> entries = new ArrayList();
        entries.add(4f);
        entries.add(3f);
        entries.add(2f);
        entries.add(3f);
        entries.add(4f);
        entries.add(1f);
        ArrayList<Float> entries2 = new ArrayList();
        entries2.add(2f);
        entries2.add(5f);
        entries2.add(2f);
        entries2.add(1f);
        entries2.add(4f);
        entries2.add(2f);
        try {
            JSONObject dataSet = new JSONObject();
            dataSet.put("labels", label.toString());
            JSONObject val = new JSONObject();
            val.put("compnay 1", entries.toString());
            val.put("company 2", entries2.toString());
            dataSet.put("values", val.toString());
            values.add(new ChartData(dataSet));
            radarChart.setData(values);
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return view;
    }
}
