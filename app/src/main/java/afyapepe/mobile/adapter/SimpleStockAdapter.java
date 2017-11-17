package afyapepe.mobile.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import afyapepe.mobile.R;
import afyapepe.mobile.activity.Stock;

/**
 * Created by Millie Agallo on 11/6/2017.
 */

public class SimpleStockAdapter  extends BaseAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private List<Stock>stockList;

    public SimpleStockAdapter(Activity activity, List<Stock> stockList){
        this.activity = activity;
        this.stockList= stockList;
    }

    @Override
    public int getCount() {
        return stockList.size();
    }

    @Override
    public Object getItem(int location) {
        return stockList.get(location);
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
            view = inflater.inflate(R.layout.list_stock,null);

        TextView Name = (TextView)view.findViewById(R.id.tvid);
        TextView Drugname = (TextView)view.findViewById(R.id.tvname2);
        TextView Quantity = (TextView)view.findViewById(R.id.tvid5);
      //  TextView Count = (TextView)view.findViewById(R.id.tv);


        Stock stock = stockList.get(position);

        Drugname.setText(stock.getDrugname());
        Name.setText(stock.getName());
        Quantity.setText(stock.getQuantity());

        return view;
//String count = ""+lstView.getAdapter().getCount(); textView.setText(count);
        //or
        //String.valueOf(lstView.getAdapter().getCount());
    }
    public void setFilter(ArrayList<Stock>newList){

        stockList = new ArrayList<>();
        stockList.addAll(newList);

        notifyDataSetChanged();
    }


}