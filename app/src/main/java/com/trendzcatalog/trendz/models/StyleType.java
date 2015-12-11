package com.trendzcatalog.trendz.models;

import java.util.Date;

/**
 * Created by kennethascheri on 11/30/15.
 */
public class StyleType {
    public int StyleTypeID;
    public String StyleTypeName;
    public String StyleTypeDesc;
    public Date DateCreated;
    public Date LastModified;
    public int UpdatedByID;
    public boolean Active;

    public StyleType(int StyleTypeID) {
        this.StyleTypeID = StyleTypeID;
    }
}
