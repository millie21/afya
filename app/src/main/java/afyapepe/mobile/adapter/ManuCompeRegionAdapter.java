package afyapepe.mobile.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import afyapepe.mobile.R;
import afyapepe.mobile.fragment.CompMonth_R;
import afyapepe.mobile.fragment.CompToday_R;
import afyapepe.mobile.fragment.CompWeek_R;
import afyapepe.mobile.fragment.CompYear_R;

/**
 * Created by Millie Agallo on 10/19/2017.
 */

public class ManuCompeRegionAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public ManuCompeRegionAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new CompToday_R();
        } else if (position == 1) {
            return new CompWeek_R();
        } else if (position == 2) {
            return new CompMonth_R();
        } else {
            return new CompYear_R();
        }

    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 4;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.fragment_manu_competiton_analysis_sales_today);
            case 1:
                return mContext.getString(R.string.fragment_manu_competiton_analysis_sales_week);
            case 2:
                return mContext.getString(R.string.fragment_manu_competiton_analysis_sales_month);
            case 3:
                return mContext.getString(R.string.fragment_manu_competiton_analysis_sales_year);
            default:
                return null;
        }
    }


}

