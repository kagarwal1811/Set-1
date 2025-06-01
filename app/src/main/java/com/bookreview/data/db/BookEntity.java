package com.bookreview.data.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "books")
public class BookEntity implements Serializable {
    @PrimaryKey @NonNull
    public String id;
    public String title;
    public String author;
    public String description;
    public float rating;
}
