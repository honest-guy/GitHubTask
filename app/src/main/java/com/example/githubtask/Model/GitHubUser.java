package com.example.githubtask.Model;

import com.google.gson.annotations.SerializedName;

public class GitHubUser {

    @SerializedName("login")
    private String login;

    @SerializedName("name")
    private String name;

    @SerializedName("followers")
    private String followers;

    @SerializedName("following")
    private String following;

    @SerializedName("avatar_url")
    private String avatar_url;

    @SerializedName("email")
    private String email;

    public GitHubUser(String login, String name, String followers, String following, String avatar_url, String email) {
        this.setLogin(login);
        this.setName(name);
        this.setFollowers(followers);
        this.setFollowing(following);
        this.setAvatar_url(avatar_url);
        this.setEmail(email);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getAvatar_url() {
        return avatar_url;
    }
    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
