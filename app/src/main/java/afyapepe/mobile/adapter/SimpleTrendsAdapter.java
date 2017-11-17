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

public class SimpleTrendsAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> trendsList;

    public SimpleTrendsAdapter(Activity activity, List<Stock> trendsList){
        this.activity = activity;
        this.trendsList= trendsList;
    }

    @Override
    public int getCount() {
        return trendsList.size();
    }

    @Override
    public Object getItem(int location) {
        return trendsList.get(location);
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
            view = inflater.inflate(R.layout.list_trends,null);

        TextView Manufacturer = (TextView)view.findViewById(R.id.tvposition);//
        TextView Totalq = (TextView)view.findViewById(R.id.tvid);


        Stock stock = trendsList.get(position);

        Manufacturer.setText(stock.getManufacturer());
        Totalq.setText(stock.getTotalq());

        return view;

    }


}