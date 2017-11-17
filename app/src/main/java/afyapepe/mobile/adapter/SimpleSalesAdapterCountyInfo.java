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
 * Created by Millie Agallo on 11/16/2017.
 */

public class SimpleSalesAdapterCountyInfo extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> salesListr;

    public SimpleSalesAdapterCountyInfo(Activity activity, List<Stock> salesListr){
        this.activity = activity;
        this.salesListr= salesListr;
    }

    @Override
    public int getCount() {
        return salesListr.size();
    }

    @Override
    public Object getItem(int location) {
        return salesListr.get(location);
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
            view = inflater.inflate(R.layout.list_county_info,null);

        TextView Total = (TextView)view.findViewById(R.id.tvposition);//
    //    TextView Drugname = (TextView)view.findViewById(R.id.tvname2);
        // TextView Name = (TextView)view.findViewById(R.id.tvname394);
        TextView FacilityName = (TextView)view.findViewById(R.id.tvpos);
        TextView Pharmacy = (TextView)view.findViewById(R.id.tvname3);
        TextView County = (TextView)view.findViewById(R.id.tvid);

        Stock stock = salesListr.get(position);

//        Drugname.setText(stock.getDrugname());
        Pharmacy.setText(stock.getPharmacy());
        Total.setText(stock.getTotal());
        // Name.setText(stock.getName());
        FacilityName.setText(stock.getFacilityName());
        County.setText(stock.getCounty());
        return view;

    }

}
