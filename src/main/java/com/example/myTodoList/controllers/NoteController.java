package com.example.myTodoList.controllers;

import com.example.myTodoList.model.Note;
import com.example.myTodoList.services.NoteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



import java.util.List;

@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("")
    public String startNote(Model model, HttpServletRequest request){
        String remoteUser = request.getRemoteUser();
        model.addAttribute("remoteUser", remoteUser);
        return "redirect:/note/list";
    }
    @GetMapping ("/list")
    public ModelAndView getNote(){
        ModelAndView modelAndView = new ModelAndView("list");
        List <Note> notes = this.noteService.listAll();
        modelAndView.addObject("notes", notes);
        return modelAndView;
    }

    @GetMapping("/delete/{userId}/{noteId}")
    public String removeNote(@PathVariable("userId") long userId, @PathVariable("noteId") long noteId){
        noteService.deleteById(userId, noteId);
        return "redirect:/note/list";
    }

    @GetMapping("/edit/{userId}/{noteId}")
    public String searchNote(@PathVariable("userId") long userId, @PathVariable("noteId") long noteId, Model model) {
        Note note = noteService.findByIdAndUserId(noteId, userId).orElse(null);
        if (note != null) {
            model.addAttribute("note", note);
            model.addAttribute("userId", userId);
            return "edit";
        } else {
            return "redirect:/note/list";
        }
    }

    @PostMapping("/edit/{userId}/{noteId}")
    public String editNote(@PathVariable("userId") long userId, @PathVariable("noteId") long noteId, @ModelAttribute Note note) {
        noteService.update(userId, noteId, note.getTitle(), note.getContent());
        return "redirect:/note/list";
    }
}
