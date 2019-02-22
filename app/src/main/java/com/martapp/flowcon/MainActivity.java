package com.martapp.flowcon;

import android.arch.persistence.room.Room;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.martapp.flowcon.db.AppDatabase;
import com.martapp.flowcon.db.Flow;


public class MainActivity extends AppCompatActivity {

private ViewPager viewPager;
private TabLayout tabLayout;
FragmentPagerAdapter adapterViewPager;
private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        // Create an InitializerBuilder
        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(this);

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

        // Enable command line interface
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
        );

        // Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer);


        viewPager = (ViewPager) findViewById(R.id.pager);
        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);


        //tabLayout.setupWithViewPager(viewPager);
        final TabLayout.Tab test01 = tabLayout.newTab();
        final TabLayout.Tab test02 = tabLayout.newTab();
        final TabLayout.Tab test03 = tabLayout.newTab();

        test01.setIcon(R.drawable.button);
        test02.setIcon(R.drawable.button3);
        test03.setIcon(R.drawable.note);

        tabLayout.addTab(test01,0);
        tabLayout.addTab(test02,1);
        tabLayout.addTab(test03,2);

     //   tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.colorPrimary));
     //   tabLayout.setSelectedTabIndicator(R.color.colorAccent);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        test01.setIcon(R.drawable.button);
                        test02.setIcon(R.drawable.button3);
                        test03.setIcon(R.drawable.note);
                        break;
                    case 1:
                        test01.setIcon(R.drawable.button);
                        test02.setIcon(R.drawable.button3);
                        test03.setIcon(R.drawable.note);
                        break;
                    case 2:
                        test01.setIcon(R.drawable.button);
                        test02.setIcon(R.drawable.button3);
                        test03.setIcon(R.drawable.note);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        //Flow flow = AppDatabase.getInstance(this).flowDao().getflowById(1);
        //String flowname = flow.getFlow_name();
        //Log.d("myTag","1 строка в базе: " + flowname);


    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: // Fragment # 0 - This will show FirstFragment
                    return First.newInstance(0, "Ввод данных по потокам");
                case 1: // Fragment # 0 - This will show FirstFragment different title
                    return Second.newInstance(1, "График потоков");
                case 2: // Fragment # 1 - This will show SecondFragment
                    return frag_kategorii.newInstance(2, "Список категорий");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }
}
