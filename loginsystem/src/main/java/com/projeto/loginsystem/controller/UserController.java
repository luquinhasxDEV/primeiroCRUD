package com.projeto.loginsystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController                     // 1. Avisa ao Spring que esta classe é um Controller de API REST
@RequestMapping("/api/users")    // 2. Define a rota/URL base para todas as chamadas desta classe
@CrossOrigin(origins = "http://localhost5173")      //3. Permite que o React (Vite) acesse esta API
public class UserController {


}
