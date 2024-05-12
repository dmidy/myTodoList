package com.example.myTodoList.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "notes")
@Data
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter
	private long noteId;
	@Getter @Setter
	private String title;
	@Getter @Setter
	private String content;
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	@Getter private User user;

	public Note() {
	}

	public Note(String title, String content) {
		this.title = title;
		this.content = content;
	}
}
