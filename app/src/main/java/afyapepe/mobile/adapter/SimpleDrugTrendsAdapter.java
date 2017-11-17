package afyapepe.mobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import afyapepe.mobile.R;
import afyapepe.mobile.activity.Stock;

/**
 * Created by Millie Agallo on 11/8/2017.
 */

public class SimpleDrugTrendsAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> trendsListd;

    public SimpleDrugTrendsAdapter(Activity activity, List<Stock> trendsListd){
        this.activity = activity;
        this.trendsListd= trendsListd;
    }

    @Override
    public int getCount() {
        return trendsListd.size();
    }

    @Override
    public Object getItem(int location) {
        return trendsListd.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {


        if (inflater == null)
            inflater = (LayoutInflater)activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if( view == null)
            view = inflater.inflate(R.layout.list_trend_sub,null);

        TextView Drugname = (TextView)view.findViewById(R.id.tvposition);//
        TextView Totalq = (TextView)view.findViewById(R.id.tvid);


        Stock stock = trendsListd.get(position);

        Drugname.setText(stock.getDrugname());
        Totalq.setText(stock.getTotalq());

        return view;

    }


}
