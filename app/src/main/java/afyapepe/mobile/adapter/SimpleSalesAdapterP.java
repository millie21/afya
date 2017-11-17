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
 * Created by Millie Agallo on 11/14/2017.
 */

public class SimpleSalesAdapterP  extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> salesList;

    public SimpleSalesAdapterP(Activity activity, List<Stock> salesList){
        this.activity = activity;
        this.salesList= salesList;
    }

    @Override
    public int getCount() {
        return salesList.size();
    }

    @Override
    public Object getItem(int location) {
        return salesList.get(location);
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
            view = inflater.inflate(R.layout.list_pharm,null);

        TextView Total = (TextView)view.findViewById(R.id.tvposition);//
        TextView Drugname = (TextView)view.findViewById(R.id.tvid);
        TextView FacilityName = (TextView)view.findViewById(R.id.tvpos);
        TextView Pharmacy = (TextView)view.findViewById(R.id.tvid5);
        TextView County = (TextView)view.findViewById(R.id.tvname2);


        Stock stock = salesList.get(position);

        Drugname.setText(stock.getDrugname());
        Pharmacy.setText(stock.getPharmacy());
        Total.setText(stock.getTotal());
        FacilityName.setText(stock.getFacilityName());
        County.setText(stock.getCounty());
        return view;

    }


}