package com.gabdullin.rail.testtest3.data.db;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CardDao {

    @Query("SELECT * FROM cardTable")
    List<Card> getAll();

    @Query("SELECT * FROM cardTable WHERE id = :id")
    List<Card> getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Card card);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Iterable<Card> card);

    @Delete
    void delete(Card card);

    @Query("DELETE FROM cardTable")
    void deleteAll();

}
