package com.example.taskmaster;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM TaskOld")
    List<TaskOld> getAll();

    @Query("SELECT * FROM TaskOld WHERE id=:id")
    TaskOld loadAllByIds(Long id);

    @Insert
    Long insertTask(TaskOld taskOld);


}
