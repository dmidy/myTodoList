package com.example.myTodoList.controllers;

import com.example.myTodoList.model.Note;
import com.example.myTodoList.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

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
    @PostMapping ("/delete/{id}")
    public String removeNote(@PathVariable("id") long id){
        noteService.deleteById(id);
        return "redirect:/note/list";
    }
    @GetMapping ("/edit/{id}")
    public String editNote(@PathVariable("id") long id, RedirectAttributes redirectAttributes){
        Note note = noteService.findById(id);
        redirectAttributes.addFlashAttribute("note", note);
        return "redirect:/note/edit";
    }
    @PostMapping ("/edit")
    public String saveNote(@ModelAttribute("note") Note note){
        Long id = note.getId();
        String title = note.getTitle();
        String content = note.getContent();
        noteService.update(id, title, content);
        return "redirect:/note/list";
    }
}
