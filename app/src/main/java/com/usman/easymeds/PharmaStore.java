package com.usman.easymeds;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class PharmaStore extends AppCompatActivity {
    RecyclerView recyclerView;
    searchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharma_store);


        //add back button on top
        ActionBar actionBar = getSupportActionBar();
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.back_icon);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<modelSearch> options =
                new FirebaseRecyclerOptions.Builder<modelSearch>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("easymeds_final1"), modelSearch.class)
                        .build();

        // set a GridLayoutManager with default vertical orientation and 2 number of columns
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView

        adapter = new searchAdapter(options);
        recyclerView.setAdapter(adapter);

    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.searchmenu, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchProcess(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchProcess(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void searchProcess(String s) {

        FirebaseRecyclerOptions<modelSearch> options =
                new FirebaseRecyclerOptions.Builder<modelSearch>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("easymeds_final1").orderByChild("brand_name").startAt(s.toUpperCase()).endAt(s.toUpperCase()+"\uf8ff"), modelSearch.class)
                        .build();
        adapter = new searchAdapter(options);
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }
}