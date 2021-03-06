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
 * Created by Millie Agallo on 2/12/2018.
 */

public class RegionSumCounties  extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> countyListsum;

    public RegionSumCounties(Activity activity, List<Stock> countyListsum){
        this.activity = activity;
        this.countyListsum = countyListsum;
    }

    @Override
    public int getCount() {
        return countyListsum.size();
    }

    @Override
    public Object getItem(int location) {
        return countyListsum.get(location);
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
            view = inflater.inflate(R.layout.list_county_sum,null);

        TextView Totcounty = (TextView)view.findViewById(R.id.btLeft);//

        Stock stock = countyListsum.get(position);

//        Drugname.setText(stock.getDrugname());

        Totcounty.setText(stock.getTotcounty());

        return view;

    }

}
