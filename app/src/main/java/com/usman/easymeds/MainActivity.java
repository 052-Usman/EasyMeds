package com.usman.easymeds;

import static android.view.Gravity.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.customview.widget.Openable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ImageButton menu;
    DrawerLayout dl;
    NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menu = findViewById(R.id.menu);

//        menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dl = findViewById(R.id.drawerLayout);
//                // If the navigation drawer is not open then open it, if its already open then close it.
//                if (dl.isDrawerOpen(LEFT)) {
//                    dl.closeDrawer(RIGHT);
//                } else {
//                    dl.openDrawer(LEFT);
//                }
//            }
//        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this,GetStartedScreen.class);
                MainActivity.this.startActivity(intent);
                finish();
            }
        });

//        dl = findViewById(R.id.drawerLayout);
//        nv = findViewById(R.id.navigationView);
//
//        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.nav_logout:
//                        FirebaseAuth.getInstance().signOut();
//                        Intent intent = new Intent(MainActivity.this,GetStartedScreen.class);
//                        MainActivity.this.startActivity(intent);
//                        finish();
//                        break;
//                }
//                return true;
//            }
//        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this,GetStartedScreen.class);
                MainActivity.this.startActivity(intent);
                finish();
        }
        dl.closeDrawer(GravityCompat.START);
        return true;
    }
}