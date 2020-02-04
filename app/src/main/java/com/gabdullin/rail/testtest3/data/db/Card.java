package com.gabdullin.rail.testtest3.data.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cardTable")
public class Card {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String fileName;

    public Card(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
