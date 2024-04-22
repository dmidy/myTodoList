package com.example.myTodoList.controllers;

import com.example.myTodoList.Note;
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
    @GetMapping ("/list")       //отримати список нотаток. Виводиться список нотаток (title та content), кожну нотатку можна видалити або редагувати
    public ModelAndView getNote(){
        ModelAndView modelAndView = new ModelAndView("list");
        List <Note> notes = this.noteService.listAll();
        modelAndView.addObject("notes", notes);
        return modelAndView;
    }
    @PostMapping ("/delete/{id}")       //видалити нотатку по ID. Після видалення нотатки відбувається редирект на /note/list
    public String removeNote(@PathVariable("id") long id){
        noteService.deleteById(id);
        return "redirect:/note/list";
    }
    @GetMapping ("/edit/{id}")  //сторінка редагування нотатку (відкривається по натисненню на кнопку Редагувати на списку нотаток).
    public String editNote(@PathVariable("id") long id, RedirectAttributes redirectAttributes){
        Note note = noteService.getById(id);
        redirectAttributes.addFlashAttribute("note", note);
        return "redirect:/note/edit";
    }
    @PostMapping ("/edit")        //сюди відправляється запит на редагування нотатки. Після збереження оновленого контенту нотатки відбувається редирект на /list
    public String saveNote(@ModelAttribute("note") Note note){
        noteService.update(note);
        return "redirect:/note/list";
    }
}
