package com.example.springjdbc.controller;


import com.example.springjdbc.db.SpringJdbcConfig;
import com.example.springjdbc.model.User;
import com.example.springjdbc.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SpringJdbcConfig springJdbcConfig;
    DataSource dataSource = springJdbcConfig.mysqlDataSource();


    SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("user");


    @GetMapping("/")
    public ResponseEntity count() {
        return ResponseEntity.ok(userRepository.getAllUser());
    }

    @GetMapping("/userById")
    public ResponseEntity userById(@RequestParam(name = "id") int id) {
        return ResponseEntity.ok(userRepository.getUserById(2));
    }

    @PostMapping("/addUser")
    public int addUser(User user) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("nickname", user.getName());
        return simpleJdbcInsert.execute(parameters);
    }

}
