package com.vivek.postify.controller;

import com.vivek.postify.Service.UserService;
import com.vivek.postify.modal.GenericResponse;
import com.vivek.postify.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/users")
    GenericResponse createUser(@RequestBody User user){
        userService.save(user);
        return new GenericResponse("user create and saved in db");
    }
}
