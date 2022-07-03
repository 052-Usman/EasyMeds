package com.usman.easymeds;

import static java.lang.String.valueOf;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.common.util.NumberUtils;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.util.TextUtils;

import de.hdodenhof.circleimageview.CircleImageView;

public class searchAdapter extends FirebaseRecyclerAdapter<modelSearch, searchAdapter.searchViewHolder>
{
    public searchAdapter(@NonNull FirebaseRecyclerOptions<modelSearch> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull searchViewHolder holder, int position, @NonNull modelSearch model)
    {
       holder.brand_name.setText(model.getBrand_name());
      // holder.company_name.setText(model.getCompany_name());

       String loStr = model.getGeneric_name().toLowerCase();
       holder.generic_name.setText(loStr);
       //holder.drug_category.setText(model.getDrug_category());

       String strPrice = model.getPacks_price();
       String strS = strPrice.split("\n")[0];
//      String strS = strPrice.split("\n")[0].split(":")[0];
//       String strL = strPrice.substring(strPrice.lastIndexOf(':') + 1);
//       String strR = strS + " : " + strL;
       holder.packs_price.setText(strS.substring(strS.lastIndexOf(':') + 1));


       double dbl = model.getRating();

        //set caution of blacklist medicine
        if(dbl <  2 && dbl >= 0) {
            Glide.with(holder.caution.getContext())
                    .load(R.drawable.warning_icon)
                    .into(holder.caution);
        }

        //set image with validation
       String str = model.getLink();

        if(!TextUtils.isEmpty(str) && str.endsWith("png")) {
            Glide.with(holder.link.getContext()).load(str).into(holder.link);
        }
        else{
            Glide.with(holder.link.getContext())
                    .load(R.drawable.notfoundimg)
                    .into(holder.link);
        }

        //click listener on displaying items and parse data on next activity
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, medicineDetail.class);
                intent.putExtra("brand_name", model.getBrand_name());
                intent.putExtra("company_name", model.getCompany_name());
                intent.putExtra("generic_name", model.getGeneric_name());

                if(!TextUtils.isEmpty(model.getDrug_category()))
                    intent.putExtra("drug_category", model.getDrug_category());
                else
                    intent.putExtra("drug_category", "N/A");

                intent.putExtra("packs_price", model.getPacks_price().split("\n")[0]);
                intent.putExtra("link", model.getLink());

                if(!TextUtils.isEmpty(model.getContent()))
                    intent.putExtra("content", model.getContent());
                else
                    intent.putExtra("content", "N/A");

                if(!TextUtils.isEmpty(model.getIndication_dosage()))
                    intent.putExtra("indication_dosage", model.getIndication_dosage());
                else
                    intent.putExtra("indication_dosage", "N/A");

                if(!TextUtils.isEmpty(model.getContra_indications()))
                    intent.putExtra("contra_indications", model.getContra_indications());
                else
                    intent.putExtra("contra_indications", "N/A");

                if(!TextUtils.isEmpty(model.getPrecautions()))
                    intent.putExtra("precautions", model.getPrecautions());
                else
                    intent.putExtra("precautions", "N/A");

                if(!TextUtils.isEmpty(model.getInteractions()))
                    intent.putExtra("interactions", model.getInteractions());
                else
                    intent.putExtra("interactions", "N/A");

                if(!TextUtils.isEmpty(model.getPregnancy()))
                    intent.putExtra("pregnancy", model.getPregnancy());
                else
                    intent.putExtra("pregnancy", "N/A");

                if(!TextUtils.isEmpty(model.getLactation()))
                    intent.putExtra("lactation", model.getLactation());
                else
                    intent.putExtra("lactation", "N/A");

                if(!TextUtils.isEmpty(model.getSide_effects()))
                    intent.putExtra("side_effects", model.getSide_effects());
                else
                    intent.putExtra("side_effects", "N/A");

                intent.putExtra("rating", model.getRating());

                if(!TextUtils.isEmpty(model.getReview()))
                    intent.putExtra("review", model.getReview());
                else
                    intent.putExtra("review", "N/A");

                context.startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public searchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine,parent,false);
       return new searchViewHolder(view);
    }

    class searchViewHolder extends RecyclerView.ViewHolder
    {
        TextView brand_name;
        TextView company_name;
        TextView generic_name;
        TextView drug_category;
        ImageView link, caution;
        TextView packs_price;
        public searchViewHolder(@NonNull View itemView)
        {
            super(itemView);
            link = itemView.findViewById(R.id.link);
            caution = itemView.findViewById(R.id.caution);
            brand_name = itemView.findViewById(R.id.brand_name);
           // company_name = itemView.findViewById(R.id.company_name);
            generic_name = itemView.findViewById(R.id.generic_name);
           // drug_category = itemView.findViewById(R.id.drug_category);
            packs_price = itemView.findViewById(R.id.packs_price);

        }
    }
}
