package com.example.githubtask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.githubtask.Adapter.IssuesAdapter;
import com.example.githubtask.Adapter.ReposAdapter;
import com.example.githubtask.Model.GitHubIssues;
import com.example.githubtask.Model.GitHubRepo;
import com.example.githubtask.Utils.ApiClient;
import com.example.githubtask.Utils.GitHubIssuesEndPoint;
import com.example.githubtask.Utils.GitHubRepoEndPoint;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IssuesActivity extends AppCompatActivity {
    String redUserName;
    TextView userName;
    Bundle extras;
    RecyclerView mRecyclerView;
    List<GitHubIssues> issueDataSource = new ArrayList<>();
    RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issues);
        extras = getIntent().getExtras();
        redUserName = extras.getString("username");
        userName = findViewById(R.id.IssueUserName);
        userName.setText("User: " + redUserName);
        mRecyclerView = findViewById(R.id.issue_RecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new IssuesAdapter(issueDataSource, R.layout.list_item_issues,
                getApplicationContext());

        mRecyclerView.setAdapter(myAdapter);

        loadIssues();


    }

    public void loadIssues() {
        GitHubIssuesEndPoint apiService =
                ApiClient.getClient().create(GitHubIssuesEndPoint.class);

        Call<List<GitHubIssues>> call = apiService.getIssue(redUserName);
        call.enqueue(new Callback<List<GitHubIssues>>() {
            @Override
            public void onResponse(Call<List<GitHubIssues>> call, Response<List<GitHubIssues>> response) {

                issueDataSource.clear();
                issueDataSource.addAll(response.body());
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GitHubIssues>> call, Throwable t) {
                // Log error here since request failed
                Log.e("Issues", t.toString());
            }

        });
    }
}