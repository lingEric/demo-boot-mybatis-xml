package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> save(User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all/{pn}")
    public ResponseEntity<Object> selectAll(@PathVariable Integer pn) {
        PageHelper.startPage(pn, 10);
        List<User> users = userService.selectAll();
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }
}
