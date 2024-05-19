package com.example.myTodoList.dto.create;

import lombok.Data;

@Data
public class CreateNoteRequest {
    private String title;
    private String content;

    public CreateNoteRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}