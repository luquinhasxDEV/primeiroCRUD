package com.projeto.loginsystem.model;

import jakarta.persistence.*;


@Entity                                                 // Indica que esta classe vira uma tabela no banco
@Table(name = "tb_users")                               //Nome da tabela no PostgreSQL
public class User {

    @Id                                                 // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O ID é incremental
    private Long id;

    @Column(nullable = false)                           //O nome é obrigatório
    private String name;

    @Column(nullable = false, unique = true)            // E-mail deve ser único e obrigatório
    private String email;

    @Column(nullable = false)                           // Senha deve ser obrigatória
    private String password;

    public User() {}

    public User(String name, String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}