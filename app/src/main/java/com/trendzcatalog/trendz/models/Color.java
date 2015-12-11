package com.trendzcatalog.trendz.models;

import java.util.Date;

/**
 * Created by kennethascheri on 11/30/15.
 */
public class Color {
    public int ColorID;
    public String ColorName;
    public String ColorDesc;
    public Date DateCreated;
    public Date LastModified;
    public int UpdatedByID;
    public boolean Active;

    public Color(int ColorID) {
        this.ColorID = ColorID;
    }
}
