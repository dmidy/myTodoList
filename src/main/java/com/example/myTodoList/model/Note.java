package com.example.myTodoList.model;

import jakarta.persistence.*;


@Entity
@Table(name = "note")
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String content;

	public long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Note() {
	}

	public Note(String title, String content) {
		this.title = title;
		this.content = content;
	}

//	@Override
//	public String toString() {
//		return "Note{" +
//				"id=" + id +
//				", title='" + title + '\'' +
//				", content='" + content + '\'' +
//				'}';
//	}

//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//
//		Note note = (Note) o;
//
//		if (id != note.id) return false;
//		if (title != null ? !title.equals(note.title) : note.title != null) return false;
//		return content != null ? content.equals(note.content) : note.content == null;
//	}
}
