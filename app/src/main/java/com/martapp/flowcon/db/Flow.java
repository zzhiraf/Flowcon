package com.martapp.flowcon.db;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(indices={
        @Index(value="id")
       })



public class Flow {

    @PrimaryKey(autoGenerate = true)
    //@ColumnInfo(index = true)
    private int id;

    /**
     * Называние потока
     */
    private String flow_name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlow_name() {
        return flow_name;
    }

    public void setFlow_name(String flow_name) {
        this.flow_name = flow_name;
    }
}