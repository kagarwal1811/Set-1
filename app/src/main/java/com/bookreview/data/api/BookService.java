package com.bookreview.data.api;

import com.bookreview.data.model.BookDto;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BookService {
    @GET("/books")
    Call<List<BookDto>> getBooks();
}
