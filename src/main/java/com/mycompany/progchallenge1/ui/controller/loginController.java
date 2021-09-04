/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1.ui.controller;

import com.mycompany.progchallenge1.Application;
import com.mycompany.progchallenge1.extra.Person;
import com.mycompany.progchallenge1.io.dao.AdminRepository;
import com.mycompany.progchallenge1.io.dao.UserRepository;
import com.mycompany.progchallenge1.io.entity.Admin;
import com.mycompany.progchallenge1.io.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mahsa
 */
@RestController
public class loginController {
    @Autowired
    AdminRepository dao1;    
    @Autowired
    UserRepository dao2;
    
    @PostMapping(path = "/login")
    String login(@RequestBody Person person){//فرض بر این است که یوزرنیم های ادمین ها و یوزرها باهم متفاوت است
        //این مورد در زمان ایجاد یوزر یا ادمین جدید چک میشود
        Object obj =dao1.findByUserName(person.getUsername());
        if(obj!=null){
            Admin admin = (Admin) obj;
            if(person.getPassword().equals(admin.getPassword())){
                Application.role[0] =1;
                return "you have successfully entered as admin";
            }
            else{
                Application.role[0] =0;                                
                return "user not found";
            }
        } 
        else if((dao2.findByUserName(person.getUsername()))!=null){
            User user = dao2.findByUserName(person.getUsername());
            if(person.getPassword().equals(user.getPassword())){
                 Application.role[0] =-1;
                 Application.role[1]=user.getUserId();
                 return "you have successfully entered as user";
            }
            else{
                Application.role[0] =0;                                
                return "user not found";
           
            }
        }
        else{
            Application.role[0] =0;                        
            return "user not found";
        }
        
    }
    
    
}
