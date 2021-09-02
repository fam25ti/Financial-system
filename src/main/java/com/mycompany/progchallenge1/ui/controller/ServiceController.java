/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1.ui.controller;

import com.mycompany.progchallenge1.io.dao.ServiceRepository;
import com.mycompany.progchallenge1.io.entity.Service;
import com.mycompany.progchallenge1.io.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(path = "/services")
public class ServiceController {

    @Autowired
    private ServiceRepository dao;    
    
    @GetMapping (path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Service> getAllService(){
         return dao.findAll();
    }
    @PostMapping (path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    Service insertService(@RequestBody Service service ){
         return dao.save(service);
    }
    @PutMapping(path = "/update/servicename",produces = MediaType.APPLICATION_JSON_VALUE)
    public Service updateServiceName(@RequestParam (name = "id") Long serviceId,@RequestBody Service service){
            dao.updateNameById(service.getServiceName(),serviceId);
            return dao.findById(serviceId).get();
    }
    @PutMapping(path = "/update/servicecost",produces = MediaType.APPLICATION_JSON_VALUE)
    public Service updateServiceCost(@RequestParam (name = "id") Long serviceId,@RequestBody Service service){
            dao.updateCostById(service.getServiceCost(),serviceId);
            return dao.findById(serviceId).get();
    }
    @PutMapping(path = "/update/serviceallowed",produces = MediaType.APPLICATION_JSON_VALUE)
    public Service updateServiceAllowed(@RequestParam (name = "id") Long serviceId,@RequestBody Service service){
            dao.updateNumberOfAllowedById(service.getAllowedTimes(), serviceId);
            return dao.findById(serviceId).get();
    }
    @DeleteMapping (path = "/delete")
    String deleteByName(@RequestParam (name = "name") String name){
        if(dao.findByServiceName(name)!=null){
                    dao.deleteByServiceName(name);

                    return("deleted");
        }
        return("notfound");
    }
 
}
