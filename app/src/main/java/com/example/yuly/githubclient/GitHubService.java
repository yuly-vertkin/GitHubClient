package com.example.yuly.githubclient;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("users/{user}")
    Call<User> basicLogin(@Path("user") String user);

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    class User {
    }

    class Repo {
        @SerializedName("name")
        @Expose
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
