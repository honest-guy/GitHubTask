package com.example.githubtask.Utils;

import com.example.githubtask.Model.GitHubIssues;
import com.example.githubtask.Model.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubRepoEndPoint {
    @GET("users/{user}/repos")
    Call<List<GitHubRepo>> getRepo(@Path("user") String name);


}
