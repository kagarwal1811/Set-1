package com.bookreview.ui;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.bookreview.data.repository.BookRepository;
import com.bookreview.ui.list.BookListViewModel;
import com.bookreview.ui.detail.BookDetailViewModel;

public class VMFactory implements ViewModelProvider.Factory {
    private final BookRepository repo;
    public VMFactory(Context ctx) { repo = new BookRepository(ctx.getApplicationContext()); }

    @NonNull @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(BookListViewModel.class)) {
            return (T) new BookListViewModel(repo);
        } else if (modelClass.isAssignableFrom(BookDetailViewModel.class)) {
            return (T) new BookDetailViewModel(repo);
        }
        throw new IllegalArgumentException("Unknown VM");
    }
}
