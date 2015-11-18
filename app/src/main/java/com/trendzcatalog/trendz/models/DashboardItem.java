package com.trendzcatalog.trendz.models;

import com.trendzcatalog.trendz.R;

import java.util.ArrayList;

/**
 * Created by kennethascheri on 11/10/15.
 */
public class DashboardItem {
    public int imageResId;
    public String titleResId;
    public boolean useKevins;

    public static ArrayList<DashboardItem> getDashboardItems() {
        final ArrayList<DashboardItem> dashboardItemArrayList = new ArrayList<>(4);
        final boolean useKevins = true;
        if (!useKevins) {
            dashboardItemArrayList.add(new DashboardItem(R.drawable.ic_fillitup_01, "Fill It Up"));
            dashboardItemArrayList.add(new DashboardItem(R.drawable.ic_closet_01, "Closet"));
            dashboardItemArrayList.add(new DashboardItem(R.drawable.ic_laundry_01, "Laundry"));
            dashboardItemArrayList.add(new DashboardItem(R.drawable.ic_publish_01, "Publish"));
        } else {
            dashboardItemArrayList.add(new DashboardItem(R.drawable.ic_fillitup_2, "Fill It Up"));
            dashboardItemArrayList.add(new DashboardItem(R.drawable.ic_closet_2, "Closet"));
            dashboardItemArrayList.add(new DashboardItem(R.drawable.ic_laundryroom_2, "Laundry"));
            dashboardItemArrayList.add(new DashboardItem(R.drawable.ic_publishit_2, "Publish"));
        }
        return dashboardItemArrayList;
    }

    public DashboardItem(int imageResId, String titleResId){
        this.imageResId = imageResId;
        this.titleResId = titleResId;
    }

    public String getTitle() {
        return titleResId;
    }
}
