/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1.ui.controller;

import com.mycompany.progchallenge1.io.dao.ServiceRepository;
import com.mycompany.progchallenge1.io.dao.UserRepository;
import com.mycompany.progchallenge1.io.entity.Service;
import com.mycompany.progchallenge1.io.entity.ServicePeriod;
import com.mycompany.progchallenge1.io.entity.User;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    @Autowired
    private ServiceRepository dao2;  
    @PostMapping (path = "/add", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    User insertUser(@RequestBody User user ){
         return dao.save(user);
    }
    @GetMapping(path = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
    List<User> showAllUsers(){
        return dao.findAll();
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
    
    @GetMapping(path = "/grantservice")
    void grantServiceToUser(@RequestParam (name = "userid") Long userId,@RequestParam (name = "servicename") String serviceName){
        User user = dao.findById(userId).get();
        user.getServices().add(dao2.findByServiceName(serviceName));
        dao.save(user);
    
    }//اعطای مجوز یک سرویس با استفاده از ای دی یوزر و اسم سرویس موردنظر
    @GetMapping(path = "/grantedservices")//نمایش سرویس های مجازکاربر
    Set<Service> showGrantedServices(@RequestParam (name = "userid") Long userId){
        
        return dao.findById(userId).get().getServices();
    }
    
    @GetMapping(path = "enabledservices")//نمایش سرویس های فعال کاربر
    Set<String> showEnabledServices(@RequestParam (name = "userid") Long userId ){
        LocalDate ld = LocalDate.now();
        LocalTime lt = LocalTime.now();
        Set<String> messages=new HashSet<String>();
        StringBuilder sb= new StringBuilder("");
       // System.out.println("local date and time "+ld+"     "+lt);
        Set<Service> services = dao.getById(userId).getServices();
        for(Service service : services){
            for(ServicePeriod sp : service.getPeriods()){
                if(sp.getStatus()==true && sp.getDate().compareTo(ld)==0 &&
                        sp.getStart().compareTo(lt)<=0 && sp.getEnd().compareTo(lt)>0){
                    System.out.println(sp.getStatus()+ " "+sp.getPeriodId());
                           sb.append("Name: ").append(service.getServiceName()).append(" ,Being active period: ").append(sp.getDate())
                                   .append("  ").append(sp.getStart()).append(" - ").append(sp.getEnd());
                           messages.add(sb.toString());
                }
            }
        }
        return messages;
    
    }
    
    
    
}
