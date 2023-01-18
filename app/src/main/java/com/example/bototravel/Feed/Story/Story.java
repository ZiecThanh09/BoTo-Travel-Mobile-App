package com.example.bototravel.Feed.Story;

public class Story {

    private boolean seen;
    private String userName;

    public Story(boolean seen, String userName) {
        this.seen = seen;
        this.userName = userName;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
