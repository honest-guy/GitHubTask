package com.example.githubtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.githubtask.Adapter.ReposAdapter;
import com.example.githubtask.Model.GitHubIssues;
import com.example.githubtask.Model.GitHubRepo;
import com.example.githubtask.Utils.ApiClient;
import com.example.githubtask.Utils.GitHubRepoEndPoint;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RepositoriesActivity extends AppCompatActivity {
    String receivedUserName;
    //String newString;
    TextView userName;
    RecyclerView mRecyclerView;
    List<GitHubRepo> dataSource = new ArrayList<>();
    RecyclerView.Adapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);
        Bundle extras = getIntent().getExtras();
        receivedUserName = extras.getString("username");
        userName = findViewById(R.id.userNameTV);
        userName.setText("User: " + receivedUserName);

        mRecyclerView = findViewById(R.id.repo_RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new ReposAdapter(dataSource, R.layout.list_item_repo,
                getApplicationContext());

        mRecyclerView.setAdapter(myAdapter);

        loadRepositories();

    }



    public void loadRepositories(){
        GitHubRepoEndPoint apiService =
                ApiClient.getClient().create(GitHubRepoEndPoint.class);

        Call<List<GitHubRepo>> call = apiService.getRepo(receivedUserName);
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                dataSource.clear();
                dataSource.addAll(response.body());
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                // Log error here since request failed
                Log.e("Repos", t.toString());
            }

        });

    }

}