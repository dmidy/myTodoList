package com.example.myTodoList.dto.create;

import com.example.myTodoList.model.Note;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CreateNoteResponse {
    private Error error;

    private Note createdNote;

    public enum Error {
        OK,
        INVALID_USER,
        INVALID_TITLE,
        INVALID_CONTENT
    }

    public static CreateNoteResponse success(Note note) {
        return builder().error(Error.OK).createdNote(note).build();
    }

    public static CreateNoteResponse failed(Error error) {
        return builder().error(error).createdNote(null).build();
    }
}
