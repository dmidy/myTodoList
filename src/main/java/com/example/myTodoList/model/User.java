package com.example.myTodoList.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Column(name = "username", nullable = false)
    @NotBlank(message = "Username can`t be null")
    @Size(max = 255, message = "Username should not exceed 255 characters")
    private String username;
    @Column(name = "password", nullable = false)
    @NotBlank(message = "Password can`t be null")
    @Size(max = 255, message = "Password should not exceed 255 characters")
    private String password;
    @Column(name = "role", nullable = false)
    @NotBlank(message = "Role can`t be null")
    @Size(max = 50, message = "Role should not exceed 50 characters")
    private String role;
    @Column(name = "enabled")
    private Boolean enabled;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes = new ArrayList<>();
}
