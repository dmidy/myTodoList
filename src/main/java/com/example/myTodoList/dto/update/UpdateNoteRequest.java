package com.example.myTodoList.dto.update;

import lombok.Data;

@Data
public class UpdateNoteRequest {
    private long id;
    private String username;
    private String title;
    private String content;

    public UpdateNoteRequest(long id, String username, String title, String content) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.content = content;
    }
}