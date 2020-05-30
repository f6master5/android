package com.example.countriespenkov;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textApi = findViewById(R.id.api_label);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/rest/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<API>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<API>>() {
            @Override
            public void onResponse(Call<List<API>> call, Response<List<API>> response) {
                if(!response.isSuccessful()){
                    textApi.setText(response.code());
                    return;
                }

                List<API> posts = response.body();

                for(API api : posts){
                    String content = "";
                    content += "Наименование страны:" + "\n" + api.getName() + "\n";
                    content += "Численность: " +api.getPopulation() + "\n";
                    content += "Код страны: " + api.getAlpha2Code() + "\n\n";
                    textApi.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<API>> call, Throwable t) {
                textApi.setText(t.getMessage());
            }
        });
    }
}

