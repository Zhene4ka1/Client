package ru.bip.client1.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.*;
import ru.bip.client1.entity.BookEntity;
import ru.bip.client1.response.BaseResponse;
import ru.bip.client1.response.BookListResponse;

import java.io.IOException;

public class HTTPUtils {
    OkHttpClient client = new OkHttpClient();
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();


    public String post(String url,String json) throws IOException{
        RequestBody body = RequestBody.create(json, MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder().url(url).post(body).build();

        try(Response response = client.newCall(request).execute()){
            return response.body().string();
        }
    }

    public String get(String url, String args) throws IOException{
        Request req = new Request.Builder().url(url+args).build();

        try (Response response = client.newCall(req).execute()){
            return  response.body().string();
        }
    }

    public String put(String url, String json) throws IOException{
        RequestBody body = RequestBody.create(json,MediaType.get("application/json; charset=utf-8"));
        Request request = new Request.Builder().url(url).post(body).build();

        try (Response response = client.newCall(request).execute()){
            return  response.body().string();
        }
    }

    public String delete(String url, Integer id) throws IOException{
        Request req = new Request.Builder().url(url+"?id="+id).delete().build();

        try(Response response = client.newCall(req).execute()) {
            return  response.body().string();
        }
    }
}
