package com.example.myTodoList;

import com.example.myTodoList.services.NoteService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main  {
    @Autowired
    private NoteService noteService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @PostConstruct
    public void init() {
        Note note1 = new Note();
        note1.setTitle("Name1");
        note1.setContent("СTest text just for note");
        noteService.add(note1);

        Note note2 = new Note();
        note2.setTitle("Name2");
        note2.setContent("Project test note");
        noteService.add(note2);
    }
}