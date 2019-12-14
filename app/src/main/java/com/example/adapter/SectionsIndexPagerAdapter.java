package com.example.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.fragment.PlaceholderFragment;
import com.example.news.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsIndexPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]
            {R.string.tab_text_index_1, R.string.tab_text_index_2,
                    R.string.tab_text_index_3, R.string.tab_text_index_4,
                    R.string.tab_text_index_5, R.string.tab_text_index_6,
                    R.string.tab_text_index_7, R.string.tab_text_index_8,
                    R.string.tab_text_index_9, R.string.tab_text_index_10
            };
    private final Context mContext;

    public SectionsIndexPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;


    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 7
        return 6;
    }
}