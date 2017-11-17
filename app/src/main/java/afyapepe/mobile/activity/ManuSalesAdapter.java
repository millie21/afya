package afyapepe.mobile.activity;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import afyapepe.mobile.R;
import afyapepe.mobile.fragment.Company;
import afyapepe.mobile.fragment.Company1;
import afyapepe.mobile.fragment.Company1M;
import afyapepe.mobile.fragment.ManuByDoctor;
import afyapepe.mobile.fragment.ManuByDrug;
import afyapepe.mobile.fragment.ManuByRegion;

/**
 * Created by Millie Agallo on 10/6/2017.
 */

public class ManuSalesAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public ManuSalesAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Company();
        } else if (position == 1){
            return new Company1();

        }else{
            return new Company1M();
        }

    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 3;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.fragment_manu_sales_bydrug);
            case 1:
                return mContext.getString(R.string.fragment_manu_sales_bydoctor);
            case 2:
                return mContext.getString(R.string.fragment_manu_sales_byregion);
            default:
                return null;
        }
    }


}



