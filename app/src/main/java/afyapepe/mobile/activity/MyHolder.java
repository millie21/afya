package afyapepe.mobile.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import afyapepe.mobile.R;

class MyHolder extends RecyclerView.ViewHolder{

    TextView textFishName;
    ImageView ivFish;
    TextView textSize;
    TextView textType;
    TextView textPrice;

    // create constructor to get widget reference
    public MyHolder(View itemView) {
        super(itemView);
        textFishName= (TextView) itemView.findViewById(R.id.textFishName);
        ivFish= (ImageView) itemView.findViewById(R.id.ivFish);
        textSize = (TextView) itemView.findViewById(R.id.textSize);
        textType = (TextView) itemView.findViewById(R.id.textType);
        textPrice = (TextView) itemView.findViewById(R.id.textPrice);
    }

}