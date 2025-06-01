package com.bookreview.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bookreview.R;
import com.bookreview.data.db.BookEntity;
import com.bookreview.ui.VMFactory;

public class BookDetailActivity extends AppCompatActivity {
    private static final String EXTRA = "book";

    public static void start(Context ctx, BookEntity b){
        Intent i = new Intent(ctx, BookDetailActivity.class);
        i.putExtra(EXTRA, b);
        ctx.startActivity(i);
    }

    @Override protected void onCreate(Bundle saved){
        super.onCreate(saved);
        setContentView(R.layout.activity_book_detail);

        BookEntity book = (BookEntity) getIntent().getSerializableExtra(EXTRA);

        TextView tTitle = findViewById(R.id.title);
        TextView tDesc  = findViewById(R.id.desc);
        RatingBar rating = findViewById(R.id.rating);
        Button btnFav = findViewById(R.id.btnFav);

        BookDetailViewModel vm = new ViewModelProvider(this, new VMFactory(this)).get(BookDetailViewModel.class);
        vm.setBook(book);

        vm.book.observe(this, b -> {
            tTitle.setText(b.title);
            tDesc.setText(b.description);
            rating.setRating(b.rating);
        });

        btnFav.setOnClickListener(v -> {
            vm.favorite();
            Toast.makeText(this, "Saved to favorites!", Toast.LENGTH_SHORT).show();
        });
    }
}
