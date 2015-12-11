package com.trendzcatalog.trendz.models;

import java.util.Date;

/**
 * Created by kennethascheri on 11/30/15.
 */
public class BlogPostComment {
    public int BlogPostCommentID;
    public int BlogPostID;
    public int BlogPostCommentUserID;
    public String BlogPostCommentDesc;
    public Date DateCreated;
    public Date LastModified;
    public int UpdatedByID;
    public boolean Active;

    public BlogPostComment(int BlogPostCommentID) {
        this.BlogPostCommentID = BlogPostCommentID;
    }
}
