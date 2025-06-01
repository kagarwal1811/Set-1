package com.bookreview.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bookreview.data.db.BookEntity;
import com.bookreview.data.repository.BookRepository;

public class BookDetailViewModel extends ViewModel {
    private final BookRepository repo;
    private final MutableLiveData<BookEntity> selected = new MutableLiveData<>();
    public LiveData<BookEntity> book = selected;

    public BookDetailViewModel(BookRepository repo) { this.repo = repo; }
    public void setBook(BookEntity e) { selected.setValue(e); }
    public void favorite() { if (selected.getValue() != null) repo.addToFav(selected.getValue()); }
}
