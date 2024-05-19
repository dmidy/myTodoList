package com.example.myTodoList.dto.delete;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DeleteNoteResponse {
    private Error error;

    public enum Error {
        OK,
        NOTE_NOT_FOUND,
        INVALID_NOTE_ID,
        INSUFFICIENT_PRIVILEGES
    }

    public static DeleteNoteResponse success() {
        return builder().error(Error.OK).build();
    }

    public static DeleteNoteResponse failed(Error error) {
        return builder().error(error).build();
    }
}
