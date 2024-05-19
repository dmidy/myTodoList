package com.example.myTodoList.controllers;

import com.example.myTodoList.dto.create.CreateNoteRequest;
import com.example.myTodoList.dto.update.UpdateNoteRequest;
import com.example.myTodoList.model.Note;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import com.example.myTodoList.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;
    //REDIRECT to "/list"
    @GetMapping
    public String redirectToUserNotes(Authentication authentication) {
        String username = authentication.getName();
        return "redirect:/note/user/" + username + "/list";
    }
    // GET ALL Notes for a user and display them in a Thymeleaf template
    @GetMapping("/user/{username}/list")
    public String getUserNotesForTemplate(@PathVariable String username, Model model) {
        List<Note> notes = noteService.getUserNotes(username);
        model.addAttribute("notes", notes);
        model.addAttribute("username", username);
        return "list";
    }
    // Display form to create a new note
    @GetMapping("/user/{username}/add")
    public String addNoteForm(@PathVariable String username, Model model) {
        model.addAttribute("note", new Note());
        model.addAttribute("username", username);
        return "edit";
    }
    // Handle form submission for creating a new note
    @PostMapping("/user/{username}/add")
    public String createNoteFromForm(@PathVariable String username, @ModelAttribute Note note) {
        CreateNoteRequest request = new CreateNoteRequest(note.getTitle(), note.getContent());
        noteService.create(username, request);
        return "redirect:/note/user/" + username + "/list";
    }
    // Display form to edit an existing note
    @GetMapping("/user/{username}/edit/{noteId}")
    public String editNoteForm(@PathVariable String username, @PathVariable long noteId, Model model) {
        Note note = noteService.findById(noteId).orElse(null);
        if (note != null) {
            model.addAttribute("note", note);
            model.addAttribute("username", username);
            return "edit";
        } else {
            return "redirect:/note/user/" + username + "/list";
        }
    }
    // Handle form submission for updating an existing note
    @PostMapping("/user/{username}/edit/{noteId}")
    public String updateNoteFromForm(@PathVariable String username, @PathVariable long noteId, @ModelAttribute Note note) {
        UpdateNoteRequest request = new UpdateNoteRequest(noteId, username, note.getTitle(), note.getContent());
        noteService.update(username, request);
        return "redirect:/note/user/" + username + "/list";
    }
    // Handle request to delete a note
    @GetMapping("/user/{username}/delete/{noteId}")
    public String deleteNoteFromTemplate(@PathVariable String username, @PathVariable long noteId) {
        noteService.delete(username, noteId);
        return "redirect:/note/user/" + username + "/list";
    }
}
