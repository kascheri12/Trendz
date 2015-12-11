package com.trendzcatalog.trendz.models;

import java.util.Date;

/**
 * Created by kennethascheri on 11/30/15.
 */
public class BlogPostLove {
    public int BlogPostLoveID;
    public int BlogPostID;
    public int BlogPostLoveUserID;
    public Date DateCreated;
    public Date LastModified;
    public int UpdatedByID;
    public boolean Active;

    public BlogPostLove(int BlogPostLoveID) {
        this.BlogPostLoveID = BlogPostLoveID;
    }
}
