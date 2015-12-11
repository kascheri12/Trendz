package com.trendzcatalog.trendz.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.trendzcatalog.trendz.ColorRandomizer;
import com.trendzcatalog.trendz.R;
import com.trendzcatalog.trendz.adapters.FragmentPagerAdapterExt;
import com.trendzcatalog.trendz.adapters.ViewPagerAdapter;

import java.util.Arrays;

import ru.noties.scrollable.CanScrollVerticallyDelegate;
import ru.noties.scrollable.OnScrollChangedListener;
import ru.noties.scrollable.ScrollableLayout;

//import android.support.v4.app.FragmentManager;
//import android.support.v4.view.ViewPager;


/**
 * Created by kennethascheri on 10/25/15.
 */
public class ScrollableFragment extends Fragment {

    public static ScrollableFragment newInstance() {
        final Bundle bundle = new Bundle();

        final ScrollableFragment fragment = new ScrollableFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle sis) {
        final View view = inflater.inflate(R.layout.fragment_scrollable, parent, false);
        ScrollableLayout scrollableLayout = findView(view, R.id.scrollable_layout);
        final ViewPager header = findView(view, R.id.header_view_pager);
        final ViewPager pager = findView(view, R.id.view_pager);
        final SmartTabLayout tabsLayout = (SmartTabLayout) view.findViewById(R.id.tabs);

        //setup
        final LayoutInflater inflater2 = LayoutInflater.from(getContext());
        final Resources res = tabsLayout.getContext().getResources();

        tabsLayout.setCustomTabView(new SmartTabLayout.TabProvider() {
            @Override
            public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
                ImageView icon = (ImageView) inflater2.inflate(R.layout.custom_tab_icon1, container, false);
                switch (position) {
                    case 0:
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_home_white_24dp));
                        break;
                    case 1:
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_person_white_24dp));
                        break;
                    default:
                        throw new IllegalStateException("Invalid position: " + position);
                }
                return icon;
            }
        });

        final ViewPagerAdapter adapter = new ViewPagerAdapter(
                getChildFragmentManager(),
                getResources(),
                Arrays.asList(ListViewFragment.newInstance(), RecyclerViewFragment.newInstance())
        );

        pager.setAdapter(adapter);
        tabsLayout.setViewPager(pager);
        scrollableLayout.setCanScrollVerticallyDelegate(new CanScrollVerticallyDelegate() {
            @Override
            public boolean canScrollVertically(int direction) {
                return adapter.canScrollVertically(pager.getCurrentItem(), direction);
            }
        });

        scrollableLayout.setOnScrollChangedListener(new OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int y, int oldY, int maxY) {
                final float tabsTranslationY;
                if (y < maxY) {
                    tabsTranslationY = .0F;
                } else {
                    tabsTranslationY = y - maxY;
                }

                tabsLayout.setTranslationY(tabsTranslationY);

                header.setTranslationY(y / 2);
            }
        });

        final HeaderPagerAdapter headerPagerAdapter = new HeaderPagerAdapter(getChildFragmentManager(), 20);
        header.setAdapter(headerPagerAdapter);

        return view;
    }

    protected <V> V findView(View view, @IdRes int id) {
        //noinspection unchecked
        return (V) view.findViewById(id);
    }

    private static class HeaderPagerAdapter extends FragmentPagerAdapterExt {

        private final int mCount;
        private final ColorRandomizer mColorRandomizer;

        public HeaderPagerAdapter(FragmentManager fm, int count) {
            super(fm);
            this.mCount = count;
            this.mColorRandomizer = new ColorRandomizer(new int[] {0xFFf44336, 0xFF9c27b0, 0xFF3f51b5, 0xFF03a9f4, 0xFF8bc34a});
        }

        @Override
        public Fragment getItem(int position) {
            return HeaderFragment.newInstance(mColorRandomizer.next());
        }

        @Override
        public int getCount() {
            return mCount;
        }
    }

    public static class HeaderFragment extends Fragment {

        private static final String ARG_COLOR = "arg.Color";

        public static HeaderFragment newInstance(int color) {
            final Bundle bundle = new Bundle();
            bundle.putInt(ARG_COLOR, color);

            final HeaderFragment fragment = new HeaderFragment();
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle sis) {
            View view = inflater.inflate(R.layout.fragment_profile_header, parent, false);
            return view;
        }
    }
}

