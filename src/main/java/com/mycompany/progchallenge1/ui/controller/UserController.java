/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1.ui.controller;

import com.mycompany.progchallenge1.io.dao.UserRepository;
import com.mycompany.progchallenge1.io.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mahsa
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {
    @Autowired
    private UserRepository dao;
    
    @PostMapping(path = "/add",produces = MediaType.APPLICATION_JSON_VALUE)
    User insertUser(@RequestBody User user){
        dao.save(user);
        return user;
    }
    
    @DeleteMapping(path = "/delete")
    public String deleteUser(@RequestParam (name = "id") Long userId){
        if(dao.existsById(userId)){
            dao.deleteById(userId);
            return "user with user id = "+userId+" deleted successfully";
        }
        else{
            return "user not found";
        }
        
    }
    @PutMapping(path = "/update/username")
    public User updateUsername(@RequestParam (name = "id") Long userId,@RequestBody User user){
            
            dao.updateUsernameById(user.getUserName(),userId);
            return dao.findById(userId).get();
        
    }
    @PutMapping(path = "/update/password")
    public User updatePassword(@RequestParam (name = "id") Long userId,@RequestBody User user){
            
            dao.updatePasswordById(user.getPassword(), userId);
            return dao.findById(userId).get();
        
    }
    @PutMapping(path = "/update/credit")
    public String updateCredit(@RequestParam (name = "id") Long userId,@RequestBody User user){
            if(dao.existsById(userId)){
               dao.updateCreditById(user.getCredit(), userId);
               return "Credit updated successfully";
            }
            else{
                return "User not found";
            }
    }
    
    @GetMapping(path = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getById(@RequestParam (name = "id") Long userId){
        return dao.findById(userId).get();
    }
    
    
    
    
    
}
