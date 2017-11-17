package afyapepe.mobile.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import afyapepe.mobile.R;
import afyapepe.mobile.fragment.Company;
import afyapepe.mobile.fragment.Drug;
import afyapepe.mobile.fragment.Region;
import afyapepe.mobile.fragment.Substitutions;

/**
 * Created by Millie Agallo on 10/4/2017.
 */

public class ManuSimpleAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public ManuSimpleAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new Company();
        } else if (position == 1){
            return new Drug();
        } else if (position == 2){
            return new Region(); }
        else {
            return new Substitutions();
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
                return mContext.getString(R.string.fragment_companys);
            case 1:
                return mContext.getString(R.string.fragment_drugs);
            case 2:
                return mContext.getString(R.string.fragment_regions);
            case 3:
                return mContext.getString(R.string.fragment_substitutions);
            default:
                return null;
        }
    }


}

