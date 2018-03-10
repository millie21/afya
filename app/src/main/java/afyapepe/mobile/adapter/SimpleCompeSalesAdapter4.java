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

public class SimpleCompeSalesAdapter4 extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> sectorList4;

    public SimpleCompeSalesAdapter4(Activity activity, List<Stock> sectorList4){
        this.activity = activity;
        this.sectorList4= sectorList4;
    }

    @Override
    public int getCount() {
        return sectorList4.size();
    }

    @Override
    public Object getItem(int location) {
        return sectorList4.get(location);
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
            view = inflater.inflate(R.layout.list_compe4,null);

        TextView Manufacturer = (TextView)view.findViewById(R.id.tvpositiond);//
        TextView Quantity = (TextView)view.findViewById(R.id.tvidd);
        TextView Qprice = (TextView)view.findViewById(R.id.tvname22d);

        Stock stock = sectorList4.get(position);

        Manufacturer.setText(stock.getManufacturer());
        Quantity.setText(stock.getQuantity());
        Qprice.setText(stock.getQprice());

        return view;

    }
}

