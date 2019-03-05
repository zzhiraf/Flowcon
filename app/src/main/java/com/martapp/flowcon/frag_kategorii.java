package com.martapp.flowcon;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.martapp.flowcon.db.AppDatabase;
import com.martapp.flowcon.db.Flow;
import com.martapp.flowcon.db.Note;

import java.util.Timer;
import java.util.TimerTask;

public class frag_kategorii extends Fragment {
    private String  title;
    private int page;
    TextView potok1;
    TextView potok2;
    TextView potok3;
    TextView potok4;
    TextView potok5;
    AppDatabase DB;
    Timer timer1 = new Timer();
    Timer timer2 = new Timer();
    Timer timer3 = new Timer();
    Timer timer4 = new Timer();
    Timer timer5 = new Timer();
    final long DELAY = 1000;

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
        DB = AppDatabase.getInstance(getContext());
    }

    public String getFlowname(int flowid) {
        Flow flow = DB.flowDao().getflowById(flowid);
        String flowname = "";
        if (flow != null)
            flowname = flow.getFlow_name();
        return flowname;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.kategorii, container, false);
        potok1 = view.findViewById(R.id.potok1E);
        potok2 = view.findViewById(R.id.potok2E);
        potok3 = view.findViewById(R.id.potok3E);
        potok4 = view.findViewById(R.id.potok4E);
        potok5 = view.findViewById(R.id.potok5E);

        potok1.setText(getFlowname(1));
        potok2.setText(getFlowname(2));
        potok3.setText(getFlowname(3));
        potok4.setText(getFlowname(4));
        potok5.setText(getFlowname(5));



        potok1.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                if (s.length() >= 3) {

                    timer1 = new Timer();
                    timer1.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // TODO: do what you need here (refresh list)
                            // you will probably need to use
                            // runOnUiThread(Runnable action) for some specific
                            // actions
                            setflow(1,potok1.getText().toString());
                        }

                    }, DELAY);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(timer1 != null)
                    timer1.cancel();
            }
        });

        potok2.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() >= 3) {

                    timer1 = new Timer();
                    timer1.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // TODO: do what you need here (refresh list)
                            // you will probably need to use
                            // runOnUiThread(Runnable action) for some specific
                            // actions
                            setflow(2,potok2.getText().toString());
                        }

                    }, DELAY);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(timer2 != null)
                    timer2.cancel();
            }
        });

        potok3.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                if (s.length() >= 3) {

                    timer1 = new Timer();
                    timer1.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // TODO: do what you need here (refresh list)
                            // you will probably need to use
                            // runOnUiThread(Runnable action) for some specific
                            // actions
                            setflow(3,potok3.getText().toString());
                        }

                    }, DELAY);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(timer3 != null)
                    timer3.cancel();
            }
        });

        potok4.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                if (s.length() >= 3) {

                    timer1 = new Timer();
                    timer1.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            // TODO: do what you need here (refresh list)
                            // you will probably need to use
                            // runOnUiThread(Runnable action) for some specific
                            // actions
                            setflow(4,potok4.getText().toString());
                        }

                    }, DELAY);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(timer4 != null)
                    timer4.cancel();
            }
        });

        potok5.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста

                setflow(5,potok5.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(timer5 != null)
                    timer5.cancel();
            }
        });


        return view;
    }

    public void setflow(Integer flow_id, String flowname)

    {
        Flow flow = new Flow();

        flow.setId(flow_id);
        flow.setFlow_name(flowname);
        Log.d("myTag","flow_id:" + flow_id + " flowname:" + flowname);
        DB.flowDao().insert(flow);
    }

}
