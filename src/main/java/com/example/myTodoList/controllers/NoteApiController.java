package com.example.myTodoList.controllers;

import com.example.myTodoList.dto.create.CreateNoteRequest;
import com.example.myTodoList.dto.create.CreateNoteResponse;
import com.example.myTodoList.dto.delete.DeleteNoteResponse;
import com.example.myTodoList.dto.update.UpdateNoteRequest;
import com.example.myTodoList.dto.update.UpdateNoteResponse;
import com.example.myTodoList.model.Note;
import com.example.myTodoList.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/note")
public class NoteApiController {
    private final NoteService noteService;

    @Autowired
    public NoteApiController(NoteService noteService) {
        this.noteService = noteService;
    }
    //GET ALL
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Note>> getUserNotes(@PathVariable String username) {
        List<Note> notes = noteService.getUserNotes(username);
        return ResponseEntity.ok(notes);
    }
    //CREATE
    @PostMapping("/user/{username}")
    public ResponseEntity<CreateNoteResponse> createNote(@PathVariable String username, @RequestBody CreateNoteRequest request) {
        CreateNoteResponse response = noteService.create(username, request);
        if (response.getError() == CreateNoteResponse.Error.OK) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    //GET
    @GetMapping("/{noteId}")
    public ResponseEntity<Note> getNoteById(@PathVariable long noteId) {
        Optional<Note> note = noteService.findById(noteId);
        return note.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    //UPDATE
    @PutMapping("/{noteId}")
    public ResponseEntity<UpdateNoteResponse> updateNote(@PathVariable long noteId, @RequestBody UpdateNoteRequest request) {
        UpdateNoteResponse response = noteService.update(request.getUsername(), request);
        if (response.getError() == UpdateNoteResponse.Error.OK) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    //DELETE
    @DeleteMapping("/{noteId}")
    public ResponseEntity<DeleteNoteResponse> deleteNote(@PathVariable long noteId, @RequestParam String username) {
        DeleteNoteResponse response = noteService.delete(username, noteId);
        if (response.getError() == DeleteNoteResponse.Error.OK) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
}
