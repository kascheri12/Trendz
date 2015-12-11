package com.trendzcatalog.trendz.models;

import java.util.Date;

/**
 * Created by kennethascheri on 11/30/15.
 */
public class SubStyleType {
    public int SubStyleTypeID;
    public String SubStyleTypeName;
    public String SubStyleTypeDesc;
    public Date DateCreated;
    public Date LastModified;
    public int UpdatedByID;
    public boolean Active;

    public SubStyleType(int SubStyleTypeID) {
        this.SubStyleTypeID = SubStyleTypeID;
    }
}
