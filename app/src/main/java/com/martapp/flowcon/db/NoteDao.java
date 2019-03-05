package com.martapp.flowcon.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("select * from note order by date_create")
    List<Note> getAll();

    @Query("SELECT * FROM Note WHERE id = :id")
    Note getNoteById(long id);

    @Query("SELECT * FROM Note WHERE date_create = :date_create and flow_id=:flowId")
    Note getNoteByDateAndID(long date_create,int flowId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Note... notes);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Note note);

    @Delete
    void delete(Note note);

    @Update
    void update(Note note);
}
