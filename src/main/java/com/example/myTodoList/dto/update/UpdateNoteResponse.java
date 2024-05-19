package com.example.myTodoList.dto.update;

import com.example.myTodoList.model.Note;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UpdateNoteResponse {
    private Error error;

    private Note updatedNote;

    public enum Error {
        OK,
        NOTE_NOT_FOUND,
        INSUFFICIENT_PRIVILEGES,
        INVALID_NOTE_ID,
        INVALID_TITLE,
        INVALID_CONTENT,
        INVALID_TITLE_LENGTH,
        INVALID_CONTENT_LENGTH
    }

    public static UpdateNoteResponse success(Note updatedNote) {
        return builder().error(Error.OK).updatedNote(updatedNote).build();
    }

    public static UpdateNoteResponse failed(Error error) {
        return builder().error(error).updatedNote(null).build();
    }
}