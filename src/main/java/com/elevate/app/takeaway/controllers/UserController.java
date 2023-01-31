package com.elevate.app.takeaway.controllers;

import com.elevate.app.takeaway.model.ResponseModel;
import com.elevate.app.takeaway.model.UserModel;
import com.elevate.app.takeaway.model.signInModel;
import com.elevate.app.takeaway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseModel createUser(@Valid @RequestBody UserModel userModel) {
        ResponseModel model = new ResponseModel();
        model.id = userService.createUser(userModel);
        model.message = "User Created";
        model.responseCode = HttpStatus.CREATED.value();
        return model;
    }

    @PutMapping("/update/{userId}")
    public ResponseModel updateUser(@Valid @RequestBody UserModel userModel, @PathVariable long userId) {
        ResponseModel model = new ResponseModel();
        model.id = userService.updateUser(userModel, userId);
        model.message = "User Updated";
        model.responseCode = HttpStatus.CREATED.value();
        return model;
    }

    @GetMapping("/users")
    public ResponseModel getAllUsers() {
        ResponseModel model = new ResponseModel();
        model.data = Collections.singletonList(userService.getAllUsers());
        model.message = "Users Found";
        model.responseCode = HttpStatus.CREATED.value();
        return model;
    }

    @GetMapping("/city")
    public ResponseModel getUsersByCity(@RequestParam String city) {
        ResponseModel model = new ResponseModel();
        model.data = Collections.singletonList(userService.getUsersByCity(city));
        model.message = "Users Found";
        model.responseCode = HttpStatus.CREATED.value();
        return model;
    }

    @GetMapping("/login")
    public ResponseModel signInByParam(@RequestParam String username ,
                                   @RequestParam String password) {
        ResponseModel model = new ResponseModel();
        model.data = Collections.singletonList(userService.signIn(username, password));
        model.message = "User logged in";
        model.responseCode = HttpStatus.OK.value();
        return model;
    }

    @PostMapping("/signIn")
    public ResponseModel loginByObject(@Valid @RequestBody signInModel signInModel) {
        ResponseModel model = new ResponseModel();
        model.data= Collections.singletonList(userService.signIn(signInModel.getUserName(), signInModel.getPassword()));
        model.message = "User logged in";
        model.responseCode = HttpStatus.OK.value();
        return model;
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseModel deleteUser(@PathVariable long userId){
        ResponseModel model = new ResponseModel();
        userService.delete(userId);
        model.message = "User Deleted";
        model.responseCode = HttpStatus.UNAUTHORIZED.value();
        return model;
    }
}
