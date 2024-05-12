package com.example.myTodoList.repository;

import com.example.myTodoList.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("SELECT n FROM Note n WHERE n.noteId = :noteId AND n.user.userId = :userId")
    Optional<Note> findByIdAndUserId(@Param("noteId") long noteId, @Param("userId") long userId);
}
