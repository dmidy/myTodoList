package com.example.myTodoList.services;

import com.example.myTodoList.dto.create.CreateNoteRequest;
import com.example.myTodoList.dto.update.UpdateNoteRequest;
import com.example.myTodoList.dto.create.CreateNoteResponse;
import com.example.myTodoList.dto.delete.DeleteNoteResponse;
import com.example.myTodoList.dto.update.UpdateNoteResponse;
import com.example.myTodoList.model.Note;
import com.example.myTodoList.model.User;
import com.example.myTodoList.repository.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserService userService;

    @Transactional
    public CreateNoteResponse create(String username, CreateNoteRequest request) {
    Optional<CreateNoteResponse.Error> validationError = validateCreateFields(request);
    if (validationError.isPresent()) {
        return CreateNoteResponse.failed(validationError.get());
    }

    User user = userService.findByUsername(username);
    if (user == null) {
        return CreateNoteResponse.failed(CreateNoteResponse.Error.INVALID_USER);
    }

    Note newNote = Note.builder()
            .user(user)
            .title(request.getTitle())
            .content(request.getContent())
            .build();
    Note savedNote = noteRepository.save(newNote);

    return CreateNoteResponse.success(savedNote);
}

    public List<Note> getUserNotes(String username) {
        return noteRepository.findByUserUsername(username);
    }

    @Transactional
    public UpdateNoteResponse update(String username, UpdateNoteRequest request) {
        Optional<Note> optionalNote = noteRepository.findById(request.getId());
        if (optionalNote.isEmpty()) {
            return UpdateNoteResponse.failed(UpdateNoteResponse.Error.INVALID_NOTE_ID);
        }

        Note note = optionalNote.get();
        if (!note.getUser().getUsername().equals(username)) {
            return UpdateNoteResponse.failed(UpdateNoteResponse.Error.INSUFFICIENT_PRIVILEGES);
        }

        Optional<UpdateNoteResponse.Error> validationError = validateUpdateFields(request);
        if (validationError.isPresent()) {
            return UpdateNoteResponse.failed(validationError.get());
        }

        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        noteRepository.save(note);

        return UpdateNoteResponse.success(note);
    }

    @Transactional
    public DeleteNoteResponse delete(String username, long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isEmpty()) {
            return DeleteNoteResponse.failed(DeleteNoteResponse.Error.INVALID_NOTE_ID);
        }

        Note note = optionalNote.get();
        if (!note.getUser().getUsername().equals(username)) {
            return DeleteNoteResponse.failed(DeleteNoteResponse.Error.INSUFFICIENT_PRIVILEGES);
        }

        noteRepository.delete(note);
        return DeleteNoteResponse.success();
    }

    private Optional<CreateNoteResponse.Error> validateCreateFields(CreateNoteRequest request) {
        if (StringUtils.isBlank(request.getTitle()) || request.getTitle().length() > 255) {
            return Optional.of(CreateNoteResponse.Error.INVALID_TITLE);
        }
        if (StringUtils.length(request.getContent()) > 1000) {
            return Optional.of(CreateNoteResponse.Error.INVALID_CONTENT);
        }
        return Optional.empty();
    }

    private Optional<UpdateNoteResponse.Error> validateUpdateFields(UpdateNoteRequest request) {
        if (StringUtils.length(request.getTitle()) > 255) {
            return Optional.of(UpdateNoteResponse.Error.INVALID_TITLE_LENGTH);
        }
        if (StringUtils.length(request.getContent()) > 1000) {
            return Optional.of(UpdateNoteResponse.Error.INVALID_CONTENT_LENGTH);
        }
        return Optional.empty();
    }

    public Optional<Note> findById(long id) {
        return noteRepository.findById(id);
    }
}