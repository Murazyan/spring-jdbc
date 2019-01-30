package com.example.springjdbc.controller;


import com.example.springjdbc.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public ResponseEntity count(){
        return ResponseEntity.ok(userRepository.getAllUser());
    }

    
    @GetMapping("/userById")
    public ResponseEntity userById(@RequestParam(name = "id")int id){
        return ResponseEntity.ok(userRepository.getUserById(2));
    }

}
