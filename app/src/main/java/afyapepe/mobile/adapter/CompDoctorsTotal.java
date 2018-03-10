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
 * Created by Millie Agallo on 2/6/2018.
 */

public class CompDoctorsTotal extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> sectorListt;

    public CompDoctorsTotal (Activity activity, List<Stock> sectorList){
        this.activity = activity;
        this.sectorListt= sectorList;
    }

    @Override
    public int getCount() {
        return sectorListt.size();
    }

    @Override
    public Object getItem(int location) {
        return sectorListt.get(location);
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
            view = inflater.inflate(R.layout.list_compe_doctor_tot,null);


        TextView Totalqb = (TextView)view.findViewById(R.id.testing12);


        Stock stock = sectorListt.get(position);

        Totalqb.setText(stock.getTotalqb());


        return view;

    }

}
