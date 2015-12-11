package com.trendzcatalog.trendz.models;

import java.util.Date;

/**
 * Created by kennethascheri on 11/30/15.
 */
public class BlogPost {
    public int BlogPostID;
    public int BlogPostUserID;
    public int CombinationID;
    public Date DateCreated;
    public Date LastModified;
    public int UpdatedByID;
    public boolean Active;

    public BlogPost(int BlogPostID) {
        this.BlogPostID = BlogPostID;
    }
}
