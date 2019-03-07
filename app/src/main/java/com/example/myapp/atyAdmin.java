package com.example.myapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class atyAdmin extends AppCompatActivity {
    private String[] mMenuTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty_admin);

        mMenuTitles = getResources().getStringArray(R.array.menu);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerList = findViewById(R.id.left_drawer);

        mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item,mMenuTitles));
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
    }

    public void selectItem(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = new fragAdmin(); // default fragment
        switch (position) {
            case 0: fragment = new fragAdmin(); break;
            case 1: fragment = new fragEditProperty(); break;
            case 2: fragment = new fragEditRequest(); break;
            case 3: fragment = new fragCheck(); break;
            case 4: fragment = new fragEditUserRole(); break;
        }
        transaction.replace(R.id.content_frame, fragment).commit();
        mDrawerList.setItemChecked(position,true);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}
