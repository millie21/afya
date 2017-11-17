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
 * Created by Millie Agallo on 11/7/2017.
 */

public class SimpleSalesAdapterD extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> salesListd;

    public SimpleSalesAdapterD(Activity activity, List<Stock> salesListd){
        this.activity = activity;
        this.salesListd= salesListd;
    }

    @Override
    public int getCount() {
        return salesListd.size();
    }

    @Override
    public Object getItem(int location) {
        return salesListd.get(location);
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
            view = inflater.inflate(R.layout.list_doc,null);

       // TextView Total = (TextView)view.findViewById(R.id.tvposition);//
        TextView Drugname = (TextView)view.findViewById(R.id.tvid);
        TextView Name = (TextView)view.findViewById(R.id.tvname394);
        TextView FacilityName = (TextView)view.findViewById(R.id.tvpos);
        TextView Pharmacy = (TextView)view.findViewById(R.id.tvid5);
        TextView County = (TextView)view.findViewById(R.id.tvname3);
        TextView Quantity = (TextView)view.findViewById(R.id.tv);


        Stock stock = salesListd.get(position);

        Drugname.setText(stock.getDrugname());
        Pharmacy.setText(stock.getPharmacy());
       // Total.setText(stock.getTotal());
        Name.setText(stock.getName());
        FacilityName.setText(stock.getFacilityName());
        County.setText(stock.getCounty());
        Quantity.setText(stock.getQuantity());
        return view;

    }


}