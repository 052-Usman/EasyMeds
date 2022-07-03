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
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button orderNow, blackListFind;
    ImageButton menu;
    ImageView searchView;

    DrawerLayout dl;
    NavigationView nv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

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

        orderNow = findViewById(R.id.orderNow);
        orderNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PharmaStore.class);
                MainActivity.this.startActivity(intent);
            }
        });

        blackListFind = findViewById(R.id.blackListFind);
        blackListFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,blackList.class);
                MainActivity.this.startActivity(intent);
            }
        });

        searchView = findViewById(R.id.searchView);
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PharmaStore.class);
                MainActivity.this.startActivity(intent);
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
        Toast.makeText(MainActivity.this, "Home is clicked", Toast.LENGTH_SHORT).show();

        switch (item.getItemId()){

            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this,GetStartedScreen.class);
                MainActivity.this.startActivity(intent);
                finish();
                return true;


            case R.id.nav_home:
                Toast.makeText(MainActivity.this, "Home is clicked", Toast.LENGTH_SHORT).show();
                return true;

        }
        dl.closeDrawer(GravityCompat.START);
        return true;


    }
}