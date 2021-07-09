package com.example.mvvmarchitectureroompractice.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmarchitectureroompractice.Entitys.Note;
import com.example.mvvmarchitectureroompractice.Repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> allnotes;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        this.repository = new NoteRepository(application);
        this.allnotes = repository.getAllNotes();
    }

    public  void insert(Note note){
        repository.insert(note);
    }

    public void Update(Note note){
        repository.Update(note);
    }
    public void delete(Note note){
        repository.delete(note);
    }
    public  void deleteAllNotes(){
        repository.deleteAllNodes();
    }

    public LiveData<List<Note>> getAllnotes(){
        return allnotes;
    }
}
