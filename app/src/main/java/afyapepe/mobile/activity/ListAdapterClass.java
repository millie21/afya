package afyapepe.mobile.activity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import afyapepe.mobile.R;

/**
 * Created by Agallo on 18-Sep-17.
 */

public class ListAdapterClass extends BaseAdapter {

    Context context;
    List<Employee> valueList;

    public ListAdapterClass(List<Employee> listValue, Context context)
    {
        this.context = context;
        this.valueList = listValue;
    }

//    @Override
//    public int getCount()
//    {
//        return this.valueList.size();
//    }



    @Override
    public Object getItem(int position)
    {
        return this.valueList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }
    @Override
    public int getCount() {
        return this.valueList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewItem viewItem = null;

        if(convertView == null)
        {
            viewItem = new ViewItem();

            LayoutInflater layoutInfiater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInfiater.inflate(R.layout.manu_employee_listview, null);

            viewItem.TextViewTaskName = (TextView)convertView.findViewById(R.id.textView1);

            convertView.setTag(viewItem);
        }
        else
        {
            viewItem = (ViewItem) convertView.getTag();
        }

        viewItem.TextViewTaskName.setText(valueList.get(position).EmployeeName);

        return convertView;
    }
}

class ViewItem
{
    TextView TextViewTaskName;


}

