package com.martapp.flowcon.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface FlowDao {

    @Query("select * from flow")
    List<Flow> getAll();

    @Query("SELECT * FROM flow WHERE id = :id")
    Flow getflowById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Flow... notes);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Flow flow);

    @Delete
    void delete(Flow flow);

    @Update
    void update(Flow flow);
}

