package com.bookreview.ui.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.bookreview.data.db.BookEntity;
import com.bookreview.data.repository.BookRepository;
import com.bookreview.util.Resource;

import java.util.List;

public class BookListViewModel extends ViewModel {
    public final LiveData<Resource<List<BookEntity>>> books;
    public BookListViewModel(BookRepository repo) { books = repo.getBooks(); }
}
