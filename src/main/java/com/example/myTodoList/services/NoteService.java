package com.example.myTodoList.services;

import com.example.myTodoList.model.Note;
import com.example.myTodoList.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    public void add(String title, String content) {
        Note newNote = new Note();
        newNote.setTitle(title);
        newNote.setContent(content);
        noteRepository.save(newNote);
    }
    public Note findById(long id) {
        return noteRepository.findById(id);
    }

    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }
    public void update (Long id, String title, String content){
        Note editNote = noteRepository.findById(id);
        editNote.setTitle(title);
        editNote.setContent(content);
        noteRepository.save(editNote);
    }
}