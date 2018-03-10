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
 * Created by Millie Agallo on 2/12/2018.
 */

public class RegionSumDoctors extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock> doctorListsum;

    public RegionSumDoctors(Activity activity, List<Stock> doctorListsum){
        this.activity = activity;
        this.doctorListsum = doctorListsum;
    }

    @Override
    public int getCount() {
        return doctorListsum.size();
    }

    @Override
    public Object getItem(int location) {
        return doctorListsum.get(location);
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
            view = inflater.inflate(R.layout.list_doctors_sum,null);

        TextView Totdoctors = (TextView)view.findViewById(R.id.btLeft2);//

        Stock stock = doctorListsum.get(position);

//        Drugname.setText(stock.getDrugname());

        Totdoctors.setText(stock.getTotdoctors());

        return view;

    }

}
