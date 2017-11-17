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

public class SimpleRegionTrendsAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> trendsListr;

    public SimpleRegionTrendsAdapter(Activity activity, List<Stock> trendsListr){
        this.activity = activity;
        this.trendsListr= trendsListr;
    }

    @Override
    public int getCount() {
        return trendsListr.size();
    }

    @Override
    public Object getItem(int location) {
        return trendsListr.get(location);
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
            view = inflater.inflate(R.layout.list_trend_r,null);

        TextView County = (TextView)view.findViewById(R.id.tvposition);//
        TextView Totalq = (TextView)view.findViewById(R.id.tvid);


        Stock stock = trendsListr.get(position);

        Totalq.setText(stock.getTotalq());
        County.setText(stock.getCounty());

        return view;

    }


}
