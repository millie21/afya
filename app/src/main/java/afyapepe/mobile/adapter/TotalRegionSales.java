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

public class TotalRegionSales extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> salesTotals;

    public TotalRegionSales(Activity activity, List<Stock> salesTotals){
        this.activity = activity;
        this.salesTotals= salesTotals;
    }

    @Override
    public int getCount() {
        return salesTotals.size();
    }

    @Override
    public Object getItem(int location) {
        return salesTotals.get(location);
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
            view = inflater.inflate(R.layout.list_totals_sum,null);

        TextView TotalIncome = (TextView)view.findViewById(R.id.btLeft);//

        Stock stock = salesTotals.get(position);

        TotalIncome.setText(stock.getTotalIncome());

        return view;

    }//salesTotals

}
