package com.example.myTodoList.controllers;

import com.example.myTodoList.model.Note;
import com.example.myTodoList.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @GetMapping("")
    public String startNote(){
        return "redirect:/note/list";
    }
    @GetMapping ("/list")
    public ModelAndView getNote(){
        ModelAndView modelAndView = new ModelAndView("list");
        List <Note> notes = this.noteService.listAll();
        modelAndView.addObject("notes", notes);
        return modelAndView;
    }

    @GetMapping("/add")
    public String addNotePage(Model model) {
        long newNoteId = noteService.add("","");
        return String.format("redirect:/note/edit/%d", newNoteId);
    }

    @PostMapping ("/delete/{id}")
    public String removeNote(@PathVariable("id") long id){
        noteService.deleteById(id);
        return "redirect:/note/list";
    }
    @GetMapping("/edit/{id}")
    public String editNote(@PathVariable("id") long id, Model model) {
        Optional<Note> noteOptional = noteService.findById(id);
        if (noteOptional.isPresent()) {
            Note note = noteOptional.get();
            model.addAttribute("note", note);
            return "edit";
        } else {
            model.addAttribute("errorMessage", "Note with id " + id + " not found");
            return "errorList";
        }
    }

    @GetMapping("/edit")
    public ModelAndView editNotePage(@ModelAttribute("note") Note note) {
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("note", note);
        return modelAndView;
    }

    @PostMapping ("/edit")
    public String saveNote(@ModelAttribute("note") Note note){
        Long id = note.getId();
        System.out.println("Received id: " + id);
        String title = note.getTitle();
        String content = note.getContent();
        noteService.update(id, title, content);
        return "redirect:/note/list";
    }
}
