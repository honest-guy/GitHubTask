package com.example.githubtask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.githubtask.Model.GitHubUser;
import com.example.githubtask.Utils.ApiClient;
import com.example.githubtask.Utils.GitHubUserEndPoints;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    TextView userName;
    TextView following;
    TextView followers;
    TextView login;
    TextView email;
    ImageView avatar;
    Button userRepos;
    Bundle extras;
    String newString;
    Bitmap myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        avatar = (ImageView) findViewById(R.id.avatar);
        userName = (TextView) findViewById(R.id.username);
        following = (TextView) findViewById(R.id.following);
        followers = (TextView) findViewById(R.id.followers);
        login = (TextView) findViewById(R.id.logIn);
        email = (TextView) findViewById(R.id.email);
        userRepos = (Button) findViewById(R.id.ReposBt);
        extras = getIntent().getExtras();
        newString = extras.getString("STRING I NEED");
        System.out.println(newString);
        loadData();

    }



    public void loanOwnRepos(View view) {
        Intent intent  = new Intent(UserActivity.this, RepositoriesActivity.class);
        intent.putExtra("username", newString);
        startActivity(intent);
    }



    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    public void loadData() {
        final GitHubUserEndPoints apiService =
                ApiClient.getClient().create(GitHubUserEndPoints.class);

        Call<GitHubUser> call = apiService.getUser(newString);
        call.enqueue(new Callback<GitHubUser>() {

            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser>
                    response) {

                ImageDownloader task = new ImageDownloader();

                try {
                    myImage = task.execute(response.body().getAvatar_url()).get();

                } catch (Exception e) {

                    e.printStackTrace();

                }

                avatar.setImageBitmap(myImage);
                avatar.getLayoutParams().height=220;
                avatar.getLayoutParams().width=220;

                if(response.body().getName() == null){
                    userName.setText("No name provided");
                } else {
                    userName.setText("Username: " + response.body().getName());
                }

                followers.setText("Followers: " + response.body().getFollowers());
                following.setText("Following: " + response.body().getFollowing());
                login.setText("LogIn: " + response.body().getLogin());

                if(response.body().getEmail() == null){
                    email.setText("No email provided");
                } else{
                    email.setText("Email: " + response.body().getEmail());
                }



            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {
                System.out.println("Failed!" + t.toString());
            }
        });
    }
}