package com.usman.easymeds;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class medicineDetail extends AppCompatActivity {

    Bundle extras;
    String brand_name, company_name, generic_name, drug_category, link, packs_price;
    String content, indication_dosage, contra_indications, precautions, interactions, pregnancy;
    String lactation, side_effects, review;
    Double rating;

    List<modelDetail> movieList = new ArrayList<>();

    TextView brand_name_set;
    TextView company_name_set;
    TextView generic_name_set;
    TextView drug_category_set;
    ImageView link_set;
    RatingBar ratingBar_set;
    TextView packs_price_set;
    TextView content_set;

    EditText feedBack;
    Button sendFeedBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_detail);

//        LinearLayout t1 =  findViewById(R.id.content1);
//        final TextView t2 = (TextView) findViewById(R.id.content);
//
//        t1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (t2.getVisibility() == View.GONE) {
//                    t2.setVisibility(View.VISIBLE);
//                } else {
//                    t2.setVisibility(View.GONE);
//                }
//            }
//        });


        //add back button on top
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setElevation(0);
        // Customize the back button
        actionBar.setHomeAsUpIndicator(R.drawable.arrow_back_icon);
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);


        extras = getIntent().getExtras();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            brand_name = bundle.getString("brand_name");
            company_name = bundle.getString("company_name");
            generic_name = bundle.getString("generic_name");
            drug_category = bundle.getString("drug_category");
            packs_price = bundle.getString("packs_price");
            link = bundle.getString("link");

            content = bundle.getString("content");
            indication_dosage = bundle.getString("indication_dosage");
            contra_indications = bundle.getString("contra_indications");
            precautions = bundle.getString("precautions");
            interactions = bundle.getString("interactions");
            pregnancy = bundle.getString("pregnancy");
            lactation = bundle.getString("lactation");
            side_effects = bundle.getString("side_effects");
            review = bundle.getString("review");
            rating = bundle.getDouble("rating");
        }

        //make proper reviews stylish
        review = review.replace('"', '\n');
        review = review.replace('\n', '\n');


        //make list of tupples for creating expand collapse
        movieList.add(new modelDetail("Content", content));
        movieList.add(new modelDetail("Indication Dosage", indication_dosage));
        movieList.add(new modelDetail("Contra Indications", contra_indications));
        movieList.add(new modelDetail("Precautions", precautions));
        movieList.add(new modelDetail("Interactions", interactions));
        movieList.add(new modelDetail("Pregnancy", pregnancy));
        movieList.add(new modelDetail("Lactation", lactation));
        movieList.add(new modelDetail("Side Effects", side_effects));
        movieList.add(new modelDetail("Review", review));


        RecAdapter adapter = new RecAdapter(movieList);

        RecyclerView recyclerView = findViewById(R.id.recyclerView1);

        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);


        //get id of item
        brand_name_set = (TextView)findViewById(R.id.brand_name);
        company_name_set = (TextView)findViewById(R.id.company_name);
        generic_name_set = (TextView)findViewById(R.id.generic_name);
        drug_category_set = (TextView)findViewById(R.id.drug_category);
        link_set = (ImageView)findViewById(R.id.link);
        content_set = (TextView)findViewById(R.id.content);
        ratingBar_set = (RatingBar)findViewById(R.id.rating);
        packs_price_set = findViewById(R.id.packs_price);

//        //set with specific id
        brand_name_set.setText(brand_name);
        company_name_set.setText(company_name);
        generic_name_set.setText(generic_name);
        drug_category_set.setText(drug_category);
        packs_price_set.setText(packs_price.substring(packs_price.lastIndexOf(':') + 1));

        ratingBar_set.setRating(rating.floatValue());
//        content_set.setText(content);

        String str = link;
        if(!TextUtils.isEmpty(str) && str.endsWith("png")) {
            Glide.with(link_set.getContext()).load(str).into(link_set);
        }
        else{
            Glide.with(link_set)
                    .load(R.drawable.notfoundimg2)
                    .into(link_set);
        }

        //user feedback handle
        sendFeedBack = findViewById(R.id.sendFeedBack);
        sendFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedBack = findViewById(R.id.feedBack);

            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favouritemenu, menu);
        return true;
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
}