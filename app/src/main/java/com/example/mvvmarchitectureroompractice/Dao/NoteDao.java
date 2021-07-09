package com.example.mvvmarchitectureroompractice.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvmarchitectureroompractice.Entitys.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insert(Note note);

    @Update
    void upDate(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNodes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
   LiveData<List<Note>> getAllNotes();

}
