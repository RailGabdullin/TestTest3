package com.gabdullin.rail.testtest3.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Card.class}, version = 1, exportSchema = false)
public abstract class WinnersAppDataBase extends RoomDatabase {

    public abstract CardDao cardDao();
}
