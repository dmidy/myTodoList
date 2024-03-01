package com.example.myTodoList;

import com.example.myTodoList.services.NoteService;

public class Main {
    public static void main(String[] args) {
        NoteService noteService = new NoteService();

        Note note1 = new Note();
        note1.setTitle("First note");
        note1.setContent("Just nothing interesting");
        Note addedNote1 = noteService.add(note1);

        Note note2 = new Note();
        note2.setTitle("Second note");
        note2.setContent("Need buy fish, milk, meet and loperamid");
        Note addedNote2 = noteService.add(note2);

        System.out.println("All notes:");
        noteService.listAll().forEach(System.out::println);

        long noteIdToDelete = addedNote1.getId();
        noteService.deleteById(noteIdToDelete);
        System.out.println("Note ID " + noteIdToDelete + " remove");

        addedNote2.setContent("Don`t buy milk!");
        noteService.update(addedNote2);
        System.out.println("Note update: " + addedNote2);

        long noteIdToFind = addedNote2.getId();
        Note foundNote = noteService.getById(noteIdToFind);
        System.out.println("Found note: " + foundNote);
    }
}