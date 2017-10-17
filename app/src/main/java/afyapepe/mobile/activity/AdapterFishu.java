package afyapepe.mobile.activity;

import afyapepe.mobile.R;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.Collections;
import java.util.List;

public class AdapterFishu extends Adapter<ViewHolder> {
    private Context context;
    DataFish current;
    int currentPos = 0;
    List<DataFish> data = Collections.emptyList();
    private LayoutInflater inflater;

    class MyHolder extends ViewHolder {
        ImageView ivFish;
        TextView textFishName;
        TextView textPrice;
        TextView textSize;
        TextView textType;

        public MyHolder(View itemView) {
            super(itemView);
            this.textFishName = (TextView) itemView.findViewById(R.id.textFishName);
            this.ivFish = (ImageView) itemView.findViewById(R.id.ivFish);
            this.textSize = (TextView) itemView.findViewById(R.id.textSize);
            this.textType = (TextView) itemView.findViewById(R.id.textType);
            this.textPrice = (TextView) itemView.findViewById(R.id.textPrice);
        }
    }

    public AdapterFishu(Context context, List<DataFish> data) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(this.inflater.inflate(R.layout.container_fish, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        DataFish current = (DataFish) this.data.get(position);
        myHolder.textFishName.setText("Name " + current.fishName);
        myHolder.textType.setText("Second Name: " + current.catName);
        myHolder.textSize.setText("Amount Paid: " + current.sizeName);
        myHolder.textPrice.setText("Joined: " + current.price + "");
        myHolder.textPrice.setTextColor(ContextCompat.getColor(this.context, R.color.bg_login2));
        Glide.with(this.context).load(Integer.valueOf(R.drawable.imageo)).into(myHolder.ivFish);
    }

    public int getItemCount() {
        return this.data.size();
    }
}
