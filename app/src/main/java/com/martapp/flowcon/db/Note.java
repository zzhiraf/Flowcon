package com.martapp.flowcon.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    /**
     * ID потока из таблицы flow
     */
    private int flow_id;

    /**
     * дата записи
     */
    @ColumnInfo(name = "date_create")
    private Long dateCreate;

    /**
     * баллы
     */

    private int point;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getdate_create() {
        return dateCreate;
    }

    public void setdate_create(Long dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getflow_id() {
        return flow_id;
    }

    public void setflow_id(int flow_id) {
        this.flow_id = flow_id;
    }

    public int getpoint() {
        return point;
    }

    public void setpoint(int point) {
        this.point = point;
    }


    @Override
    public boolean equals(Object obj) {
        final Note note = (Note) obj;
        return this.id == note.id;
    }

}


