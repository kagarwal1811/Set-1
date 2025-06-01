package com.bookreview.ui.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bookreview.R;
import com.bookreview.data.db.BookEntity;

public class BookAdapter extends ListAdapter<BookEntity, BookAdapter.VH> {
    interface OnClick { void click(BookEntity b); }
    private final OnClick cb;
    public BookAdapter(OnClick cb) { super(DIFF); this.cb = cb; }

    static class VH extends RecyclerView.ViewHolder {
        TextView title, author;
        VH(View v) { super(v); title=v.findViewById(R.id.title); author=v.findViewById(R.id.author); }
    }

    @Override public VH onCreateViewHolder(ViewGroup p, int vt) {
        return new VH(LayoutInflater.from(p.getContext()).inflate(R.layout.row_book, p, false));
    }
    @Override public void onBindViewHolder(VH h, int pos) {
        BookEntity b = getItem(pos);
        h.title.setText(b.title);
        h.author.setText(b.author);
        h.itemView.setOnClickListener(v -> cb.click(b));
    }

    private static final DiffUtil.ItemCallback<BookEntity> DIFF = new DiffUtil.ItemCallback<BookEntity>() {
        public boolean areItemsTheSame(BookEntity o, BookEntity n){ return o.id.equals(n.id); }
        public boolean areContentsTheSame(BookEntity o, BookEntity n){ return o.equals(n); }
    };
}
