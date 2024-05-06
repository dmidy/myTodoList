package com.example.myTodoList.services;

import com.example.myTodoList.model.Note;
import com.example.myTodoList.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public long add(String title, String content) {
        Note newNote = new Note(title, content);
        Note savedNote = noteRepository.save(newNote);
        return savedNote.getId();
    }
    public Optional<Note> findById(long id) {
        return noteRepository.findById(id);
    }

    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }
    public void update(Long id, String title, String content) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            Note editNote = optionalNote.get();
            editNote.setTitle(title);
            editNote.setContent(content);
            noteRepository.save(editNote);
        } else {
            throw new IllegalArgumentException("Note with id " + id + " not found");
        }
    }
}