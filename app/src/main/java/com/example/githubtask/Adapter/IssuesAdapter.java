package com.example.githubtask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubtask.Model.GitHubIssues;

import com.example.githubtask.R;

import java.util.List;

public class IssuesAdapter extends RecyclerView.Adapter<IssuesAdapter.IssuesViewHolder>{
    private List<GitHubIssues> issues;
    private int rowLayout;
    private Context context;

    public IssuesAdapter(List<GitHubIssues> issues, int rowLayout, Context context) {
        this.issues = issues;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    public List<GitHubIssues> getIssues() {
        return issues;
    }

    public void setIssues(List<GitHubIssues> issues) {
        this.issues = issues;
    }

    public int getRowLayout() {
        return rowLayout;
    }

    public void setRowLayout(int rowLayout) {
        this.rowLayout = rowLayout;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public static class IssuesViewHolder extends RecyclerView.ViewHolder {
        LinearLayout issuesLayout;
        TextView issueTitle;
        TextView issueBody;


        public IssuesViewHolder(View v) {
            super(v);
            issuesLayout = (LinearLayout) v.findViewById(R.id.issues_item_layout);
            issueTitle = (TextView) v.findViewById(R.id.issueTitle);
            issueBody = (TextView) v.findViewById(R.id.issueDescription);

        }
    }

    @Override
    public IssuesAdapter.IssuesViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new IssuesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IssuesViewHolder holder, int position) {
        holder.issueTitle.setText(issues.get(position).getTitle());
        holder.issueBody.setText(issues.get(position).getBody());

    }


    @Override
    public int getItemCount() { return issues.size();}
}
