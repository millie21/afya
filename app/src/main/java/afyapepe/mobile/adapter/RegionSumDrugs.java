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

public class RegionSumDrugs extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> drugsListsum;

    public RegionSumDrugs(Activity activity, List<Stock> drugsListsum){
        this.activity = activity;
        this.drugsListsum = drugsListsum;
    }

    @Override
    public int getCount() {
        return drugsListsum.size();
    }

    @Override
    public Object getItem(int location) {
        return drugsListsum.get(location);
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
            view = inflater.inflate(R.layout.list_drugs_sum,null);

        TextView Totdrugs = (TextView)view.findViewById(R.id.btRight26);//

        Stock stock = drugsListsum.get(position);

//        Drugname.setText(stock.getDrugname());

        Totdrugs.setText(stock.getTotdrugs());

        return view;

    }

}
