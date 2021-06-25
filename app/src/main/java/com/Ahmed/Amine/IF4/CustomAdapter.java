package com.Ahmed.Amine.IF4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList good_id, good_name, good_quantity, good_date;

    CustomAdapter(Activity activity, Context context, ArrayList good_id, ArrayList good_name, ArrayList good_quantity, ArrayList good_date){
        this.activity = activity;
        this.context = context;
        this.good_id = good_id;
        this.good_name = good_name;
        this.good_quantity = good_quantity;
        this.good_date = good_date;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.good_id_txt.setText(String.valueOf(good_id.get(position)));
        holder.good_name_txt.setText(String.valueOf(good_name.get(position)));
        holder.good_quantity_txt.setText(String.valueOf(good_quantity.get(position)));
        holder.good_date_txt.setText(String.valueOf(good_date.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(good_id.get(position)));
                intent.putExtra("name", String.valueOf(good_name.get(position)));
                intent.putExtra("quantity", String.valueOf(good_quantity.get(position)));
                intent.putExtra("date", String.valueOf(good_date.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return good_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView good_id_txt, good_name_txt, good_quantity_txt, good_date_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            good_id_txt = itemView.findViewById(R.id.good_id_txt);
            good_name_txt = itemView.findViewById(R.id.good_name_txt);
            good_quantity_txt = itemView.findViewById(R.id.good_quantity_txt);
            good_date_txt = itemView.findViewById(R.id.good_date_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
