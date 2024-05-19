package com.example.myTodoList.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "note_id", nullable = false)
	private long noteId;
	@Column(name = "title", nullable = false)
	@NotBlank(message = "Title can`t be null")
	@Size(max = 255, message = "Title should not exceed 255 characters")
	private String title;
	@Column(name = "content")
	@Size(max = 1000, message = "Content should not exceed 1k characters")
	private String content;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private User user;
}
