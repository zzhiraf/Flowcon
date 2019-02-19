package com.martapp.flowcon.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(indices={
        @Index(value="id"),
        @Index(value="flow_id", unique = true)
})
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



    @Override
    public boolean equals(Object obj) {
        final Note note = (Note) obj;
        return this.id == note.id;
    }

    public int getFlow_id() {
        return flow_id;
    }

    public Long getDateCreate() {
        return dateCreate;
    }

    public int getPoint() {
        return point;
    }

    public void setFlow_id(int flow_id) {
        this.flow_id = flow_id;
    }

    public void setDateCreate(Long dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}


