package afyapepe.mobile.activity;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.Collections;
import java.util.List;

import afyapepe.mobile.R;
import afyapepe.mobile.activity.DataFish;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.Collections;
import java.util.List;

import afyapepe.mobile.R;
import afyapepe.mobile.activity.DataFish;

public class AllagyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataFish> data= Collections.emptyList();
    DataFish current;
    int currentPos=0;

    // create constructor to innitilize context and data sent from MainActivity
    public AllagyListAdapter(Context context, List<DataFish> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.container_allagadap, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        DataFish current=data.get(position);
        myHolder.textFishName.setText("Allergy Type: "+current.fishName);
        myHolder.textType.setText("Allergy Name: " + current.catName);
       // myHolder.textSize.setText("Age: " + current.sizeName);

        myHolder.textPrice.setText("Date: " + current.price + "");
        myHolder.textPrice.setTextColor(ContextCompat.getColor(context, R.color.bg_login2));

        // load image into imageview using glide
        //Glide.with(context).load(R.drawable.imageo).into(myHolder.ivFish);

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }



    class MyHolder extends RecyclerView.ViewHolder {

        TextView textFishName;
        //ImageView ivFish;
        TextView textSize;
        TextView textType;
        TextView textPrice;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textFishName = (TextView) itemView.findViewById(R.id.textFishNameop);
            //ivFish = (ImageView) itemView.findViewById(R.id.ivFish);
            textSize = (TextView) itemView.findViewById(R.id.textSizeop);
            textType = (TextView) itemView.findViewById(R.id.textTypeop);
            textPrice = (TextView) itemView.findViewById(R.id.textPriceop);
        }
    }




    }


