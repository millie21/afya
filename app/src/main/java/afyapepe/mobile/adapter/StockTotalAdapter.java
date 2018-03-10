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
 * Created by Millie Agallo on 2/26/2018.
 */

public class StockTotalAdapter  extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> stockListtotal;

    public StockTotalAdapter(Activity activity, List<Stock> stockListtotal){
        this.activity = activity;
        this.stockListtotal= stockListtotal;
    }

    @Override
    public int getCount() {
        return stockListtotal.size();
    }

    @Override
    public Object getItem(int location) {
        return stockListtotal.get(location);
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
            view = inflater.inflate(R.layout.list_stock_total,null);

        TextView Totalqb = (TextView)view.findViewById(R.id.testing12);

        Stock stock = stockListtotal.get(position);

        Totalqb.setText(stock.getTotalqb());


        return view;
    }
}