package com.example.githubtask.Model;

import com.google.gson.annotations.SerializedName;

public class GitHubIssues {

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    public GitHubIssues(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
