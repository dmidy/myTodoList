package com.example.myTodoList.controllers;

import com.example.myTodoList.model.Note;
import com.example.myTodoList.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping ("/delete/{id}")
    public String removeNote(@PathVariable("id") long id){
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping("/edit/{id}")
    public String searchNote(@PathVariable("id") long id, Model model) {
        Note note = noteService.findById(id).orElse(null);
        if (note != null) {
            model.addAttribute("note", note);
            return "edit";
        } else {
            return "redirect:/note/list";
        }
    }

    @PostMapping("/edit")
    public String editNote(@RequestParam("id") long id, @ModelAttribute Note note) {
        noteService.update(id, note.getTitle(), note.getContent());
        return "redirect:/note/list";
    }
}
