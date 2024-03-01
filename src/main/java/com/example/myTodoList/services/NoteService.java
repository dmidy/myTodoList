package com.example.myTodoList.services;

import com.example.myTodoList.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class NoteService {
    private final Map<Long, Note> notesMap = new HashMap<>();
    private final Random random = new Random();

    public List<Note> listAll() {
        return new ArrayList<>(notesMap.values());
    }

    public Note add(Note note) {
        long id = generateUniqueId();
        note.setId(id);
        notesMap.put(id, note);
        return note;
    }

    public void deleteById(long id) {
        if (notesMap.containsKey(id)) {
            notesMap.remove(id);
        } else {
            throw new IllegalArgumentException("Note with id " + id + " does not exist");
        }
    }

    public void update(Note note) {
        if (notesMap.containsKey(note.getId())) {
            notesMap.put(note.getId(), note);
        } else {
            throw new IllegalArgumentException("Note with id " + note.getId() + " does not exist");
        }
    }

    public Note getById(long id) {
        if (notesMap.containsKey(id)) {
            return notesMap.get(id);
        } else {
            throw new IllegalArgumentException("Note with id " + id + " does not exist");
        }
    }

    private long generateUniqueId() {
        long id = random.nextLong();
        while (notesMap.containsKey(id)) {
            id = random.nextLong();
        }
        return id;
    }
}