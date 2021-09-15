package com.example.githubtask.Utils;

import com.example.githubtask.Model.GitHubIssues;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubIssuesEndPoint {
    @GET("repos/{user}/{repo}/issues")
    Call<List<GitHubIssues>> getIssue(@Path("user") String name);
}
