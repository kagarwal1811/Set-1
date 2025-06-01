package com.bookreview.ui.list;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bookreview.R;
import com.bookreview.data.db.BookEntity;
import com.bookreview.ui.VMFactory;
import com.bookreview.ui.detail.BookDetailActivity;
import com.bookreview.util.Resource;

public class BookListActivity extends AppCompatActivity {

    private BookListViewModel vm;
    private BookAdapter adapter;
    private ProgressBar progress;

    @Override protected void onCreate(Bundle saved) {
        super.onCreate(saved);
        setContentView(R.layout.activity_book_list);

        progress = findViewById(R.id.progress);
        RecyclerView rv = findViewById(R.id.recycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BookAdapter(this::openDetail);
        rv.setAdapter(adapter);

        vm = new ViewModelProvider(this, new VMFactory(this)).get(BookListViewModel.class);
        vm.books.observe(this, res -> {
            if (res.status == Resource.Status.LOADING) {
                progress.setVisibility(View.VISIBLE);
            } else {
                progress.setVisibility(View.GONE);
                if (res.status == Resource.Status.SUCCESS) {
                    adapter.submitList(res.data);
                } else {
                    Toast.makeText(this, res.message, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void openDetail(BookEntity b) {
        BookDetailActivity.start(this, b);
    }
}
