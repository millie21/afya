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

public class RegionSumPharmacies extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> pharmacyListsum;

    public RegionSumPharmacies(Activity activity, List<Stock> pharmacyListsum){
        this.activity = activity;
        this.pharmacyListsum = pharmacyListsum;
    }

    @Override
    public int getCount() {
        return pharmacyListsum.size();
    }

    @Override
    public Object getItem(int location) {
        return pharmacyListsum.get(location);
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
            view = inflater.inflate(R.layout.list_pharmacy_sum,null);

        TextView Totpharmacies = (TextView)view.findViewById(R.id.btRight);//

        Stock stock = pharmacyListsum.get(position);

        Totpharmacies.setText(stock.getTotpharmacies());

        return view;

    }

}
