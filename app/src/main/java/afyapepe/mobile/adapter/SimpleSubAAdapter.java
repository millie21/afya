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

public class SimpleSubAAdapter  extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> subList;

    public SimpleSubAAdapter(Activity activity, List<Stock> subList){
        this.activity = activity;
        this.subList= subList;
    }

    @Override
    public int getCount() {
        return subList.size();
    }

    @Override
    public Object getItem(int location) {
        return subList.get(location);
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
       // TextView Quantity = (TextView)view.findViewById(R.id.tvid5);
        TextView Pharmacy = (TextView)view.findViewById(R.id.tvpos5);
        TextView Subdrugname = (TextView)view.findViewById(R.id.tvid10);


        Stock stock = subList.get(position);

        Drugname.setText(stock.getDrugname());
        Name.setText(stock.getName());
      //  Quantity.setText(stock.getQuantity());
        Pharmacy.setText(stock.getPharmacy());
        Subdrugname.setText(stock.getSubdrugname());

        return view;
    }
}