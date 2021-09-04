/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1.ui.controller;

import com.mycompany.progchallenge1.Application;
import com.mycompany.progchallenge1.io.dao.AdminRepository;
import com.mycompany.progchallenge1.io.dao.UserRepository;
import com.mycompany.progchallenge1.io.entity.Admin;
import com.mycompany.progchallenge1.io.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mahsa
 */
@RestController
@RequestMapping(path = "/admins")
public class AdminController extends loginController {
    @Autowired
    private AdminRepository dao;
     @Autowired
    private UserRepository dao2;
        
       

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Admin insertAdmin(@RequestBody Admin admin) {
        if (Application.role[0]==1) {
            if (dao2.findByUserName(admin.getUserName()) == null)//برای چک کردنمتفاوت بودن یوزرنیم ادمین ها و یوزرها
            {
                return dao.save(admin);
            }
        }
//        Admin adminerror = new Admin();
//        adminerror.setMessage("you are not admin");
        return null;
    }

}
