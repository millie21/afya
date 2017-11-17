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
 * Created by Millie Agallo on 11/6/2017.
 */

public class SimpleSectorAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> sectorList;

    public SimpleSectorAdapter(Activity activity, List<Stock> sectorList){
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
            view = inflater.inflate(R.layout.list_sector_d,null);

        TextView Name = (TextView)view.findViewById(R.id.tvid);
        TextView Drugname = (TextView)view.findViewById(R.id.tvposition);
        TextView Total = (TextView)view.findViewById(R.id.tvid5);


        Stock stock = sectorList.get(position);

        Drugname.setText(stock.getDrugname());
        Name.setText(stock.getName());
        Total.setText(stock.getTotal());
        return view;

    }


}