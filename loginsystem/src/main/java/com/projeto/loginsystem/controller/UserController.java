package com.projeto.loginsystem.controller;

import com.projeto.loginsystem.model.User;
import com.projeto.loginsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController                  // 1. Avisa ao Spring que esta classe é um Controller de API REST
@RequestMapping("/api/users")     // 2. Define a rota/URL base para todas as chamadas desta classe
@CrossOrigin(origins = "http://localhost:5173") // 3. Permite que o React (Vite) acesse esta API sem dar erro de CORS
public class UserController {

    @Autowired                   // 4. O Spring injeção de dependência: conecta o repositório automaticamente
    private UserRepository userRepository;

    // --- CREATE (POST) ---
    // Rota: POST http://localhost:8080/api/users/register
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        // Validação: verifica se o e-mail já está em uso
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Erro: E-mail já cadastrado!");
        }

        // Salva o novo usuário no PostgreSQL
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }

    // --- READ ALL (GET) ---
    // Rota: GET http://localhost:8080/api/users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // --- READ BY ID (GET) ---
    // Rota: GET http://localhost:8080/api/users/1
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(user -> ResponseEntity.ok().body(user))
                .orElse(ResponseEntity.notFound().build());
    }

    // --- UPDATE (PUT) ---
    // Rota: PUT http://localhost:8080/api/users/1
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userRepository.findById(id).map(user -> {user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
        }).orElse(ResponseEntity.notFound().build());
    }

    // --- DELETE (DELETE) ---
    // Rota: DELETE http://localhost:8080/api/users/1
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().body("Usuário deletado com sucesso!");
        }).orElse(ResponseEntity.notFound().build());
    }
}