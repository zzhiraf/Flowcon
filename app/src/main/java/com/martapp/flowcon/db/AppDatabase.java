package com.martapp.flowcon.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.util.Log;

@Database(entities = {Note.class,Flow.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "notes.db";

    private static AppDatabase INSTANCE;



   /* public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(final SupportSQLiteDatabase database) {
            String task = "CREATE TABLE `Note` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `date_create` INT, `flow_id` INT NOT NULL)";
            String task1 = "Drop TABLE `Note`";

            String task3 = "CREATE TABLE `Flow` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `flow_name` String)";
            String task4 = "Drop TABLE `Flow`";
            String task5 = "INSERT into `Flow` VALUES(1, 'Задача1')";
            String task6 = "INSERT into `Flow` VALUES(2, 'Задача2')";

            database.execSQL(task1);
            database.execSQL(task4);
            database.execSQL(task);
            database.execSQL(task3);

            database.execSQL(task5);
            database.execSQL(task6);
            Log.d("myTag", "migrate: ");
        }
    };*/

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract NoteDao noteDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DATABASE_NAME)
                    //.addMigrations(AppDatabase.MIGRATION_1_2)
                    //.addMigrations(AppDatabase.MIGRATION_2_3)
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public abstract FlowDao flowDao();
}

