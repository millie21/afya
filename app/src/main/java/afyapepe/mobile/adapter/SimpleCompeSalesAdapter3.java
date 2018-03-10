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
 * Created by Millie Agallo on 3/9/2018.
 */

public class SimpleCompeSalesAdapter3 extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> sectorList3;

    public SimpleCompeSalesAdapter3(Activity activity, List<Stock> sectorList3){
        this.activity = activity;
        this.sectorList3= sectorList3;
    }

    @Override
    public int getCount() {
        return sectorList3.size();
    }

    @Override
    public Object getItem(int location) {
        return sectorList3.get(location);
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
            view = inflater.inflate(R.layout.list_compe3,null);

        TextView Manufacturer = (TextView)view.findViewById(R.id.tvpositionc);//
        TextView Quantity = (TextView)view.findViewById(R.id.tvidc);
        TextView Qprice = (TextView)view.findViewById(R.id.tvname22c);

        Stock stock = sectorList3.get(position);

        Manufacturer.setText(stock.getManufacturer());
        Quantity.setText(stock.getQuantity());
        Qprice.setText(stock.getQprice());

        return view;

    }
}

