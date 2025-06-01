package com.bookreview.util;

import com.bookreview.data.model.BookDto;
import com.bookreview.data.db.BookEntity;
import java.util.ArrayList;
import java.util.List;

public class Mapper {
    public static List<BookEntity> dtoToEntity(List<BookDto> list) {
        List<BookEntity> out = new ArrayList<>();
        for (BookDto d : list) {
            BookEntity e = new BookEntity();
            e.id = d.id;
            e.title = d.title;
            e.author = d.author;
            e.description = d.description;
            e.rating = d.rating;
            out.add(e);
        }
        return out;
    }
}
