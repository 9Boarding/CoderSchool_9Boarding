package com.minhnpa.coderschool.a9boarding.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.minhnpa.coderschool.a9boarding.R;
import com.minhnpa.coderschool.a9boarding.fragment.main.BookmarkFragment;
import com.minhnpa.coderschool.a9boarding.fragment.main.HomeFragment;
import com.minhnpa.coderschool.a9boarding.fragment.main.NotificationFragment;
import com.minhnpa.coderschool.a9boarding.utils.FireBaseUtils;
import com.minhnpa.coderschool.a9boarding.utils.IntentUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawable_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.bottom_nav)
    AHBottomNavigation buttomNav;

    @BindView(R.id.navMenu)
    NavigationView navView;


    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private ActionBarDrawerToggle drawerToggle;
    private Fragment fragment = null;
    protected Class fragmentClass = HomeFragment.class;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupNavigationView();
        setupBottomtabs();
        setOnClick();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
//        bottomBar.onSaveInstanceState(outState);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private ActionBarDrawerToggle setupDrawertoggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close);
    }

    private void setupNavigationView() {
        drawerToggle = setupDrawertoggle();
        drawerLayout.addDrawerListener(drawerToggle);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                selectDrawerItem(item);
                return true;
            }
        });
    }

    private void updateNavView() {
        if (FireBaseUtils.isAuth()) {

        }
    }

    private void selectDrawerItem(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_signin:
                IntentUtils.signin(this);
                break;
            case R.id.nav_signout:
                IntentUtils.signout();
                break;
            case R.id.nav_profile:
                IntentUtils.startProfileActivity(this);
                break;
            case R.id.nav_setting:
                break;
            default:
                break;
        }
    }


    private void setOnClick() {
        buttomNav.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                switch (position) {
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
                        drawerLayout.openDrawer(GravityCompat.END);
                        break;
                    default:
//                        fragmentClass = HomeFragment.class;
                }

                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                FragmentManager manager = getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.flContainer, fragment).commit();

                return true;
            }
        });
    }

    private void setupBottomtabs() {
        AHBottomNavigationItem home = new AHBottomNavigationItem(R.string.tab_home, R.drawable.ic_tab_home, R.color.white);
        AHBottomNavigationItem noti = new AHBottomNavigationItem(R.string.tab_notification, R.drawable.ic_tab_notification, R.color.white);
        AHBottomNavigationItem bookmark = new AHBottomNavigationItem(R.string.tab_bookmark, R.drawable.ic_bookmark, R.color.white);
        AHBottomNavigationItem menu = new AHBottomNavigationItem(R.string.tab_ic_nav, R.drawable.ic_tab_nav, R.color.white);

        bottomNavigationItems.add(home);
        bottomNavigationItems.add(noti);
        bottomNavigationItems.add(bookmark);
        bottomNavigationItems.add(menu);
        buttomNav.addItems(bottomNavigationItems);
        buttomNav.setTranslucentNavigationEnabled(true);
        buttomNav.setAccentColor(R.color.black);
    }
}