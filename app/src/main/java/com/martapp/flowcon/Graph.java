package com.martapp.flowcon;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Graph extends Fragment implements OnChartValueSelectedListener{
        private String  title;
        private int page;
    EditText textrash;
    private PieChart pieChart;
    List<PieEntry> entries = new ArrayList<PieEntry>();

    public static Graph newInstance(int page, String title) {
        Graph fragmentSecond = new Graph();
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

    //Функции для определения ориентации
    public int getScreenWidthPixels() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        Display display = getActivity().getWindowManager().getDefaultDisplay();

        //return displaymetrics.widthPixels;
        return display.getWidth();
    }

    public int getScreenHeightPixels() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //return displaymetrics.heightPixels;
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        return display.getHeight();

    }
    public int getScreenOrientation() {
        int width = getScreenWidthPixels();
        int height = getScreenHeightPixels();
        Log.d("myTag","width=" + width + " height=" + height);
        if (width > height) {

            return Configuration.ORIENTATION_LANDSCAPE;
        }
        if (width < height) {

            return Configuration.ORIENTATION_PORTRAIT;
        }

        return Configuration.ORIENTATION_SQUARE;
    }

    //*******************************************************
    public void printDiag(List<PieEntry> entries){

        PieDataSet dataset = new PieDataSet(entries, "");

        PieData data = new PieData(dataset);

        int colorBlack = Color.parseColor("#000000");
        pieChart.setEntryLabelColor(colorBlack);
        //Выводим дырку бублика
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(28f);
        int colorGrey = Color.parseColor("#cdcdcd");
        pieChart.setBackgroundColor(colorGrey);

        pieChart.setRotationEnabled(true);
        //Выделение области диаграммы
        pieChart.setHighlightPerTapEnabled(true);
        //Выводить имена категорий на диаграмме
        pieChart.setDrawEntryLabels(false);

        //Выводить % от затрат
        pieChart.setUsePercentValues(true);

        //dataset.setColors(ColorTemplate.LIBERTY_COLORS); //
        pieChart.setOnChartValueSelectedListener(this);
        //Заменить цвета на нужные

        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.rgb(156,254,230));
        colors.add(Color.rgb(159,185,235));
        colors.add(Color.rgb(143,231,161));
        colors.add(Color.rgb(160,239,136));
        colors.add(Color.rgb(200,246,139));
        colors.add(Color.rgb(176,219,233));
        colors.add(Color.rgb(183,176,253));
        colors.add(Color.rgb(156,254,230));
        colors.add(Color.rgb(159,185,235));
        colors.add(Color.rgb(143,231,161));
        colors.add(Color.rgb(160,239,136));
        colors.add(Color.rgb(200,246,139));
        colors.add(Color.rgb(176,219,233));
        colors.add(Color.rgb(183,176,253));

        dataset.setColors(colors);

        dataset.setValueLinePart1OffsetPercentage(60.f);
        dataset.setValueLinePart1Length(0.2f);
        dataset.setValueLinePart2Length(0.6f);
        dataset.setXValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);
        dataset.setYValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);
        dataset.setValueTextSize(11f);
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);

        dataset.setValueTextColor(Color.BLACK);

        dataset.setSliceSpace(3f);
        dataset.setSelectionShift(12f);

        //Разрешаем рисовать рисунок в диаграмме
        pieChart.setDrawCenterText(true);


        //pieChart.setCenterText(generateCenterSpannableText());


        pieChart.animateY(500);


        pieChart.setData(data);

        pieChart.saveToGallery("/sd/mychart.jpg",85); // 85 is the quality of the image

        pieChart.setCenterTextSizePixels(15);
        pieChart.setEntryLabelTextSize(12f);

        pieChart.getDescription().setEnabled(false);

        pieChart.highlightValues(null);

        pieChart.invalidate();

        Legend l = pieChart.getLegend();
        if(getScreenOrientation() == Configuration.ORIENTATION_LANDSCAPE)
        {
            //Для горизонтального экрана
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
            l.setOrientation(Legend.LegendOrientation.VERTICAL);
            //Расположение диаграммы
            pieChart.setExtraOffsets(-10f, 0, 100f, 0);
            l.setYOffset(0f);
            l.setXOffset(15f);
        }if(getScreenOrientation() == Configuration.ORIENTATION_PORTRAIT)
        {
            //Для вертикального экрана
            l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
            l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
            l.setOrientation(Legend.LegendOrientation.VERTICAL);
            //Расположение диаграммы
            //  * @param left
            //	* @param top
            //	* @param right
            //	* @param bottom
            pieChart.setExtraOffsets(15f, -75f, 0, 0);
            l.setYOffset(80f);
            l.setXOffset(-15f);

            try {
                textrash = (EditText) getActivity().findViewById(R.id.rashifrovka);
                textrash.setVisibility(View.GONE);
            } catch (Exception e) {
            }
        }



        l.setFormSize(20f);
        l.setTextSize(20f);
        l.setDrawInside(false);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.graph, container, false);

        pieChart = (PieChart) view.findViewById(R.id.chart);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textrash = (EditText) getActivity().findViewById(R.id.rashifrovka);
    //    textrashday = (EditText) getActivity().findViewById(R.id.rashifrovkaday);
        textrash.setRawInputType(0x00000000);
        textrash.setText("");



     //   textrashday.setRawInputType(0x00000000);
     //   textrashday.setText("");

    //    UpdatePeriod();
      //  ArrayList newarray = getData();
        Object element;
        entries.clear();



        entries.add(new PieEntry(1, "Первая категория"));
        entries.add(new PieEntry(3, "Вторая категория"));
        entries.add(new PieEntry(4, "Третья категория"));
        entries.add(new PieEntry(1, "Четвертая категория"));
        entries.add(new PieEntry(6, "Пятая категория"));
        printDiag(entries);

        //l.setTypeface(mTfLight);

        ImageView imageView = (ImageView)getActivity().findViewById(R.id.ImageMainL);
      /*  imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.d("myTag","Запускаем левый пересчет");

                tekday = tekday - 1;

                UpdatePeriod();


                ArrayList newarray = getData();
                Object element;
                entries.clear();
                for (int i = 0; i < newarray.size(); i++) {
                    element = newarray.get(i);
                    String str1 = ((OperationTypeBar) element).getName();
                    String str2 = Math.round(((OperationTypeBar) element).getValue()/summanewarray*100) + "% " + Math.round(((OperationTypeBar) element).getValue()) + " руб.";
                    //Обрежем строку1, чтобы диаграмма не сместилась
                    String str3 = "";
                    if (str1.length() > 20) {
                        str3 = String.format("%s %s", str1.substring(0,20), str2);}
                    else {
                        str3 = String.format("%s %s", str1, str2);
                    }

                    entries.add(new PieEntry(((OperationTypeBar) element).getValue(), str3));
                }

                pieChart.clear();
                printDiag(entries);
//				pieChart.postInvalidate();

            }
        });

        ImageView imageView1 = (ImageView)getActivity().findViewById(R.id.ImageMainR);
        imageView1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Log.d("myTag","Запускаем правый пересчет");
                EditText textrashday = (EditText) getActivity().findViewById(R.id.rashifrovkaday);
                tekday = tekday + 1;

                UpdatePeriod();

                ArrayList newarray = getData();
                Object element;
                entries.clear();
                for (int i = 0; i < newarray.size(); i++) {
                    element = newarray.get(i);
                    String str1 = ((OperationTypeBar) element).getName();
                    String str2 = Math.round(((OperationTypeBar) element).getValue()) + " руб.";
                    //Обрежем строку1, чтобы диаграмма не сместилась
                    String str3 = "";
                    if (str1.length() > 20) {
                        str3 = String.format("%s %s", str1.substring(0,20), str2);}
                    else {
                        str3 = String.format("%s %s", str1, str2);
                    }

                    entries.add(new PieEntry(((OperationTypeBar) element).getValue(), str3));
                }

                pieChart.clear();
                printDiag(entries);


            }
        });
*/
    }

    @Override
    public void onValueSelected(Entry entry, Highlight highlight) {
        if (entry == null)
            return;

        EditText textrash = (EditText) getActivity().findViewById(R.id.rashifrovka);

        textrash.setText(((PieEntry) entry).getLabel());
    }

    @Override
    public void onNothingSelected() {
        EditText textrash = (EditText) getActivity().findViewById(R.id.rashifrovka);
        Log.i("PieChart", "nothing selected");
        textrash.setText("");
    }
}
