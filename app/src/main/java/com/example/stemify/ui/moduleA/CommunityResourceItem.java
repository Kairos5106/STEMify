package com.example.stemify.ui.moduleA;

public class CommunityResourceItem {
    String title, description, author;
    int authorImageId, resourceImageId;

    public CommunityResourceItem(String title, String description, String author, int authorImageId, int resourceImageId) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.authorImageId = authorImageId;
        this.resourceImageId = resourceImageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAuthorImageId() {
        return authorImageId;
    }

    public void setAuthorImageId(int authorImageId) {
        this.authorImageId = authorImageId;
    }

    public int getResourceImageId() {
        return resourceImageId;
    }

    public void setResourceImageId(int resourceImageId) {
        this.resourceImageId = resourceImageId;
    }
}
