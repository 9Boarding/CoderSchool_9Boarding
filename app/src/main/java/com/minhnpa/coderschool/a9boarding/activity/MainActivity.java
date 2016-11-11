package com.minhnpa.coderschool.a9boarding.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.fragment.main.BookmarkFragment;
import com.minhnpa.coderschool.a9boarding.fragment.main.HomeFragment;
import com.minhnpa.coderschool.a9boarding.fragment.main.NotificationFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

//    @BindView(R.id.tabMain)
//    TabLayout tabs;

    private DatabaseReference mDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        startActivity(LoginActivity.newIntent(this));

        setupBottomtabs();
        setOnClick();

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();


//        setupTabs();
    }

//    private void setupTabs() {
//        TabLayout.Tab home = tabs.newTab();
//        TabLayout.Tab bm = tabs.newTab();
//        TabLayout.Tab noti = tabs.newTab();
//        TabLayout.Tab nav = tabs.newTab();
//
//        home.setText("Home");
//        nav.setText("Nav");
//        bm.setText("Bookmark");
//        noti.setText("Notification");
//
//
//
//    }


    private void setOnClick() {
        bottomBar.setOnTabClickListener(new OnTabClickListener() {
            @Override
            public void onTabSelected(int position) {
                Fragment fragment = null;
                Class fragmentClass = HomeFragment.class;


                switch (position){
                    case 0:
                        fragmentClass = HomeFragment.class;
                        break;
                    case 1:
                        fragmentClass = NotificationFragment.class;
                        break;
                    case 2:
                        fragmentClass = BookmarkFragment.class;
                        break;
                    case 3:

                        //TODO: show rightMenu
                        break;
                    default:
                        fragmentClass = HomeFragment.class;
                }

                try{
                    fragment = (Fragment)fragmentClass.newInstance();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.flContainer, fragment).commit();

                bottomBar.selectTabAtPosition(position,true);
            }

            @Override
            public void onTabReSelected(int position) {

            }
        });
    }

    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void setupBottomtabs() {
        bottomBar.setItems(R.menu.tab_bottombar);

        bottomBar = BottomBar.attach(this,null);
        bottomBar.setItems(R.menu.tab_bottombar);
        bottomBar.setBackgroundColor(getResources().getColor(R.color.white));
        bottomBar.setActiveTabColor(R.color.white);
        bottomBar.useDarkTheme();

        bottomBar.mapColorForTab(0, R.color.white);
        bottomBar.mapColorForTab(1, R.color.black);
        bottomBar.mapColorForTab(2, R.color.black);
        bottomBar.mapColorForTab(3, R.color.black);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        bottomBar.onSaveInstanceState(outState);
    }
}
