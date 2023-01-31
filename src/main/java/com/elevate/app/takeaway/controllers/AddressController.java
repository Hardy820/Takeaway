package com.elevate.app.takeaway.controllers;

import com.elevate.app.takeaway.model.ResponseModel;
import com.elevate.app.takeaway.model.UserAddressModel;
import com.elevate.app.takeaway.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    
    @Autowired
    UserService userService;
    
    @GetMapping("/user")
    public ResponseModel getAddressesById(@RequestParam long userId) {
        ResponseModel model = new ResponseModel();
        model.data = Collections.singletonList(userService.getUserAddressById(userId));
        model.message = "Users Found";
        model.responseCode = HttpStatus.OK.value();
        return model;
    }

    @PostMapping("/create")
    public ResponseModel createAddress(@Valid @RequestBody UserAddressModel userModel) {
        ResponseModel model = new ResponseModel();
        model.id = userService.createUserAddress(userModel);
        model.message = "User Address Created";
        model.responseCode = HttpStatus.CREATED.value();
        return model;
    }

    @PutMapping("/update/{addressId}")
    public ResponseModel updateAddress(@Valid @RequestBody UserAddressModel userModel, @PathVariable long addressId) {
        ResponseModel model = new ResponseModel();
        model.id = userService.updateUserAddress(userModel, addressId);
        model.message = "User Address Updated";
        model.responseCode = HttpStatus.OK.value();
        return model;
    }
}
