package com.example.mvvmarchitectureroompractice.RoomDatabase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mvvmarchitectureroompractice.Dao.NoteDao;
import com.example.mvvmarchitectureroompractice.Entitys.Note;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Note.class},version = 1,exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;

    public  abstract NoteDao noteDao();


    public static synchronized NoteDatabase getInstance(Context context){
        if(instance ==  null){
            instance = Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            NoteDao noteDao = instance.noteDao();

            //Background list adding
            final ExecutorService executorService = Executors.newSingleThreadExecutor();
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        noteDao.insert(new Note("Title 1","Descreiption 1",1));
                        noteDao.insert(new Note("Title 2","Descreiption 2",2));
                        noteDao.insert(new Note("Title 3","Descreiption 3",3));
                    }
                });


        }
    };





}
