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
 * Created by Millie Agallo on 2/28/2018.
 */

public class SimpleSubAAdapter2 extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> subList2;

    public SimpleSubAAdapter2(Activity activity, List<Stock> subList2){
        this.activity = activity;
        this.subList2= subList2;
    }

    @Override
    public int getCount() {
        return subList2.size();
    }

    @Override
    public Object getItem(int location) {
        return subList2.get(location);
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
            view = inflater.inflate(R.layout.list_manudrug,null);

        TextView Name = (TextView)view.findViewById(R.id.tvid);
        TextView Drugname = (TextView)view.findViewById(R.id.tvid8);
        TextView Quantity = (TextView)view.findViewById(R.id.tvid5);
        TextView Pharmacy = (TextView)view.findViewById(R.id.tvpos5);
        TextView Drugnames = (TextView)view.findViewById(R.id.tvid10);


        Stock stock = subList2.get(position);

        Drugname.setText(stock.getDrugname());
        Name.setText(stock.getName());
        Quantity.setText(stock.getQuantity());
        Pharmacy.setText(stock.getPharmacy());
        Drugnames.setText(stock.getDrugnames());

        return view;
    }
}