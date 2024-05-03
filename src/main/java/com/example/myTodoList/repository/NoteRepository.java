package com.example.myTodoList.repository;

import com.example.myTodoList.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> { ;
}
