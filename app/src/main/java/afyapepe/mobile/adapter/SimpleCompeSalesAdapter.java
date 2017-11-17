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
 * Created by Millie Agallo on 11/9/2017.
 */

public class SimpleCompeSalesAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> sectorList;

    public SimpleCompeSalesAdapter(Activity activity, List<Stock> sectorList){
        this.activity = activity;
        this.sectorList= sectorList;
    }

    @Override
    public int getCount() {
        return sectorList.size();
    }

    @Override
    public Object getItem(int location) {
        return sectorList.get(location);
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
            view = inflater.inflate(R.layout.list_compe,null);

        TextView Manufacturer = (TextView)view.findViewById(R.id.tvposition);//
        TextView Quantity = (TextView)view.findViewById(R.id.tvid);
        TextView Qprice = (TextView)view.findViewById(R.id.tvname22);

        Stock stock = sectorList.get(position);

        Manufacturer.setText(stock.getManufacturer());
        Quantity.setText(stock.getQuantity());
        Qprice.setText(stock.getQprice());

        return view;

    }



}
