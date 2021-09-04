/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1.ui.controller;

import com.mycompany.progchallenge1.Application;
import com.mycompany.progchallenge1.io.dao.LogServiceRepository;
import com.mycompany.progchallenge1.io.dao.ServicePeriodRepository;
import com.mycompany.progchallenge1.io.dao.ServiceRepository;
import com.mycompany.progchallenge1.io.dao.UserServiceRepository;
import com.mycompany.progchallenge1.io.entity.LogService;
import com.mycompany.progchallenge1.io.entity.Service;
import com.mycompany.progchallenge1.io.entity.ServicePeriod;
import com.mycompany.progchallenge1.io.entity.User;
import com.mycompany.progchallenge1.io.entity.UserService;
import java.time.Duration;
import java.util.List;
import java.util.Set;
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
public class ServiceController extends loginController{

    @Autowired
    private ServiceRepository dao; 
    @Autowired
    private ServicePeriodRepository dao2;
    @Autowired
    private LogServiceRepository dao3;
    @Autowired 
    private UserServiceRepository dao4;

    @GetMapping (path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Service> getAllService(){
        if(loginCheck()==1){
            return dao.findAll();
        
        }
        return null;
    }
    @PostMapping (path = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    Service insertService(@RequestBody Service service ){
         if(loginCheck()==1){
             return dao.save(service);

        }
         return null;
    }
    @PutMapping(path = "/update/servicename",produces = MediaType.APPLICATION_JSON_VALUE)
    public Service updateServiceName(@RequestParam (name = "id") Long serviceId,@RequestBody Service service){
            if(loginCheck()==1){
                dao.updateNameById(service.getServiceName(),serviceId);
                return dao.findById(serviceId).get();
        }
            return null;
    }
    @PutMapping(path = "/update/servicecost",produces = MediaType.APPLICATION_JSON_VALUE)
    public Service updateServiceCost(@RequestParam (name = "id") Long serviceId,@RequestBody Service service){
            if(loginCheck()==1){
                dao.updateCostById(service.getServiceCost(),serviceId);
                return dao.findById(serviceId).get();
        }
            return null;

    }
    @PutMapping (path = "/update/serviceallowed",produces = MediaType.APPLICATION_JSON_VALUE)
    public Service updateServiceAllowed(@RequestParam (name = "id") Long serviceId,@RequestBody Service service){
            if(loginCheck()==1){
                    dao.updateNumberOfAllowedById(service.getAllowedTimes(), serviceId);
                    return dao.findById(serviceId).get();
        }
            return null;

    }
    @DeleteMapping (path = "/delete")
    String deleteByName(@RequestParam (name = "servicename") String name){
        if(loginCheck()==1){
                    if(dao.existsServiceByServiceName(name)){
            Service service =dao.findByServiceName(name);
            Set<UserService> userservices =  service.getUserservices();
            for(UserService us: userservices){
               dao4.deleteUserService(service.getServiceId());
            }
            Set<ServicePeriod> serviceperiod = service.getPeriods();
            for(ServicePeriod sp: serviceperiod)
                dao2.deleteServicePeriod(service.getServiceId());
           
            return("deleted");
        }
        return("service not found");
        }
        return null;
    }
    @PostMapping(path = "/defineserviceperiod")//to enable service in a specific time
    Service definePeriodsForService(@RequestParam (name = "id") Long serviceId,@RequestBody ServicePeriod serper){
        if(loginCheck()==1){
            long elapsedSeconds = Duration.between(serper.getStart(), serper.getEnd()).toSeconds();
       if(elapsedSeconds>0 && elapsedSeconds<=12*3600){
            Service service = dao.findById(serviceId).get();
       for(ServicePeriod sp: service.getPeriods()){
           if(sp.getDate().compareTo(serper.getDate())==0){
               return null;//because just a priod in a day can be taken for a service
           }
        }
       service.getPeriods();
       serper.setService(service);
       serper.setStatus(true);
       dao2.save(serper); 
       service.getPeriods().add(serper);
       //dao.save(service);
        

       return service;
    
        }
       return null;
        }
       return null;
        
    }
    @GetMapping(path="/service",produces = MediaType.APPLICATION_JSON_VALUE)
    Service getServiceByName(@RequestParam (name = "name") String name){
        if(loginCheck()==1){
        return dao.findByServiceName(name);
        }
        return null;
    }
    @GetMapping(path = "/disableservice",produces = MediaType.APPLICATION_JSON_VALUE)//to disable a service in a specific time
    ServicePeriod disableServiceInPeriodByNameService(@RequestParam (name = "id") Long periodId){
        if(loginCheck()==1){
            ServicePeriod sp = dao2.findById(periodId).get();
            sp.setStatus(false);
            dao2.save(sp);
            return sp;    
        }
        return null;

    }
    @GetMapping(path = "/enableservice",produces = MediaType.APPLICATION_JSON_VALUE)//to enable a service in a specific time
    ServicePeriod enableServiceInPeriodByNameService(@RequestParam (name = "id") Long periodId){
        if(loginCheck()==1){
            ServicePeriod sp = dao2.findById(periodId).get();
            sp.setStatus(true);
            dao2.save(sp);
            return sp;
        }
        return null;
    }    
    @GetMapping(path = "/report/serviceusages")//گزارش گیری از استفاده سرویس ها توسط ادمین
    List<LogService> reportServiceUsage(){
        if(loginCheck()==1){
            return dao3.findAll();
        
        }
        return null;
    } 
}

    
 

