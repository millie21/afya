package afyapepe.mobile.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import afyapepe.mobile.R;

/**
 * Created by Millie Agallo on 10/6/2017.
 */

public class ManuEmployeeAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public ManuEmployeeAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ManuShowAllEmployees();
        } else {
            return new ManuAddEmployee();

        }

    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 2;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.fragment_manu_view_employee);
            case 1:
                return mContext.getString(R.string.fragment_manu_add_employee);

            default:
                return null;
        }
    }


}


