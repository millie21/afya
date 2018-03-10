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

public class RegionSumPrescriptions extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> prescriptionsListsum;

    public RegionSumPrescriptions(Activity activity, List<Stock> prescriptionsListsum){
        this.activity = activity;
        this.prescriptionsListsum = prescriptionsListsum;
    }

    @Override
    public int getCount() {
        return prescriptionsListsum.size();
    }

    @Override
    public Object getItem(int location) {
        return prescriptionsListsum.get(location);
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
            view = inflater.inflate(R.layout.list_prescriptions_sum,null);

        TextView Totprescriptions = (TextView)view.findViewById(R.id.btRight2);//

        Stock stock = prescriptionsListsum.get(position);


        Totprescriptions.setText(stock.getTotprescriptions());

        return view;

    }

}
