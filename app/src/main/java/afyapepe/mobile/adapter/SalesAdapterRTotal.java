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
 * Created by Millie Agallo on 1/24/2018.
 */

public class SalesAdapterRTotal extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> totalsaleslist;

    public SalesAdapterRTotal(Activity activity, List<Stock> totalsaleslist){
        this.activity = activity;
        this.totalsaleslist= totalsaleslist;
    }

    @Override
    public int getCount() {
        return totalsaleslist.size();
    }

    @Override
    public Object getItem(int location) {
        return totalsaleslist.get(location);
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

        TextView TotalIncome = (TextView)view.findViewById(R.id.totalcash);

        Stock stock = totalsaleslist.get(position);

        TotalIncome.setText(stock.getTotalIncome());
        return view;

    }



}
