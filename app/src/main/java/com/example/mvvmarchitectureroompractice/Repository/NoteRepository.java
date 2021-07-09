package com.example.mvvmarchitectureroompractice.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.mvvmarchitectureroompractice.Dao.NoteDao;
import com.example.mvvmarchitectureroompractice.Entitys.Note;
import com.example.mvvmarchitectureroompractice.RoomDatabase.NoteDatabase;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    final ExecutorService executorService = Executors.newSingleThreadExecutor();


    //Insert Note
    public  void insert(Note note){
        //for background only
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.insert(note);

            }

        });
        

    }
    //Update Note
    public void Update(Note note){
        //for background only
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.upDate(note);
            }
        });


    }
    //deleteNote
    public void delete(Note note){
        //for background only
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.delete(note);
            }
        });

    }

    //DeleteNode
    public void deleteAllNodes(){
        //for background only
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.deleteAllNodes();
            }
        });

    }


    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }


}
