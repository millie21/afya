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
 * Created by Millie Agallo on 3/6/2018.
 */

public class RegionDocTotal extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> regionDocTotal;

    public RegionDocTotal(Activity activity, List<Stock> regionDocTotal){
        this.activity = activity;
        this.regionDocTotal= regionDocTotal;
    }

    @Override
    public int getCount() {
        return regionDocTotal.size();
    }

    @Override
    public Object getItem(int location) {
        return regionDocTotal.get(location);
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
            view = inflater.inflate(R.layout.list_region_sales_totals,null);

        TextView Ttb = (TextView)view.findViewById(R.id.totalcash);

        Stock stock = regionDocTotal.get(position);

        Ttb.setText(stock.getTtb());

        return view;

    }



}
