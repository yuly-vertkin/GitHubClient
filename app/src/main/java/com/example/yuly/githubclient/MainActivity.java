package com.example.yuly.githubclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.yuly.githubclient.GitHubService.Repo;
import static com.example.yuly.githubclient.GitHubService.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = (TextView) findViewById(R.id.text);
        final EditText user = (EditText) findViewById(R.id.user);
        final EditText password = (EditText) findViewById(R.id.password);

        findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = user.getText().toString();
                String passwordStr = password.getText().toString();

                GitHubServiceManager.setCredentials(userName, passwordStr);
                Call<User> loginCall = GitHubServiceManager.getService().basicLogin(userName);
                loginCall.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful()) {
                            // user object available
                            text.setText(text.getText() + "\n" + "Authorization success!");
                        } else {
                            // error response, no access to resource?
                            text.setText("Authorization error!");
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                    }
                });

                Call<List<Repo>> reposCall = GitHubServiceManager.getService().listRepos(userName);
                reposCall.enqueue(new Callback<List<Repo>>() {
                    @Override
                    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                        List<Repo> repos = response.body();
                        StringBuilder res = new StringBuilder();
                        for (Repo repo : repos) {
                            res.append(repo.getName()).append("\n");
                        }
                        text.setText(text.getText() + "\n" + res.toString());
                    }

                    @Override
                    public void onFailure(Call<List<Repo>> call, Throwable t) {
                    }
                });
            }
        });
    }

}
