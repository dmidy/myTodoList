package com.example.myTodoList.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long userId;
    @Getter @Setter
    @Column(name = "username")
    private String userName;
    @Getter @Setter
    private String password;
    @Getter @Setter
    private String role;
    @Getter @Setter
    private Boolean enabled;
}
