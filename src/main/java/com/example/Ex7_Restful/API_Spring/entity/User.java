package com.example.Ex7_Restful.API_Spring.entity;

import com.example.Ex7_Restful.API_Spring.model.enum1.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data // lombok
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

//    @Column(nullable = false)
//    private Integer tokenVersion = 0;
}
