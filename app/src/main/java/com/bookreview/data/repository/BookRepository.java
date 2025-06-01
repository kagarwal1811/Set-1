package com.bookreview.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.bookreview.data.api.BookService;
import com.bookreview.data.api.FakeInterceptor;
import com.bookreview.data.db.AppDatabase;
import com.bookreview.data.db.BookDao;
import com.bookreview.data.db.BookEntity;
import com.bookreview.data.model.BookDto;
import com.bookreview.util.Mapper;
import com.bookreview.util.Resource;

import java.util.List;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookRepository {
    private final BookService api;
    private final BookDao dao;

    public BookRepository(Context ctx) {
        OkHttpClient ok = new OkHttpClient.Builder()
                .addInterceptor(new FakeInterceptor(ctx))
                .addInterceptor(new HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit r = new Retrofit.Builder()
                .baseUrl("https://fake.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(ok)
                .build();
        api = r.create(BookService.class);
        dao = AppDatabase.get(ctx).bookDao();
    }

    public LiveData<List<BookEntity>> getFavorites() { return dao.getAll(); }

    public LiveData<Resource<List<BookEntity>>> getBooks() {
        MutableLiveData<Resource<List<BookEntity>>> live = new MutableLiveData<>();
        live.postValue(Resource.loading());
        api.getBooks().enqueue(new Callback<List<BookDto>>() {
            public void onResponse(Call<List<BookDto>> call, Response<List<BookDto>> res) {
                if (res.isSuccessful() && res.body()!=null) {
                    live.postValue(Resource.success(Mapper.dtoToEntity(res.body())));
                } else {
                    live.postValue(Resource.error("API error"));
                }
            }
            public void onFailure(Call<List<BookDto>> call, Throwable t) {
                live.postValue(Resource.error(t.getMessage()));
            }
        });
        return live;
    }

    public void addToFav(BookEntity e) {
        Executors.newSingleThreadExecutor().execute(() -> dao.insert(e));
    }
}
