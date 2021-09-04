/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1.ui.controller;

import com.mycompany.progchallenge1.Application;
import com.mycompany.progchallenge1.io.dao.AdminRepository;
import com.mycompany.progchallenge1.io.dao.LogServiceRepository;
import com.mycompany.progchallenge1.io.dao.ServiceRepository;
import com.mycompany.progchallenge1.io.dao.UserRepository;
import com.mycompany.progchallenge1.io.dao.UserServiceRepository;
import com.mycompany.progchallenge1.io.entity.LogService;
import com.mycompany.progchallenge1.io.entity.Service;
import com.mycompany.progchallenge1.io.entity.ServicePeriod;
import com.mycompany.progchallenge1.io.entity.User;
import com.mycompany.progchallenge1.io.entity.UserService;
import com.mycompany.progchallenge1.io.entity.UserServiceId;
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
public class UserController extends loginController {

    @Autowired
    private UserRepository dao;
    @Autowired
    private ServiceRepository dao2;
    @Autowired
    private UserServiceRepository dao3;
    @Autowired
    private LogServiceRepository dao4;
    @Autowired
    private AdminRepository dao5;

    @PostMapping(path = "/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    User insertUser(@RequestBody User user) {
        if (Application.role[0]==1) {
            if (dao5.findByUserName(user.getUserName()) == null)//برای چک کردنمتفاوت بودن یوزرنیم ادمین ها و یوزرها
            {
                return dao.save(user);
            }
        }
        return null;
    }

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    List<User> showAllUsers() {
        if (Application.role[0]==1) {
            return dao.findAll();
        }
        return null;
    }

    @DeleteMapping(path = "/delete")
    public String deleteUser(@RequestParam(name = "id") Long userId) {
        if (Application.role[0]==1) {
            if (dao.existsById(userId)) {
                dao.deleteById(userId);
                return "user with user id = " + userId + " deleted successfully";
            } else {
                return "user not found";
            }
        }

        return null;
    }

    @PutMapping(path = "/update/username")
    public User updateUsername(@RequestParam(name = "id") Long userId, @RequestBody User user) {
        if (Application.role[0]==1) {
            dao.updateUsernameById(user.getUserName(), userId);
            return dao.findById(userId).get();
        }
        return null;

    }

    @PutMapping(path = "/update/password")
    public User updatePassword(@RequestParam(name = "id") Long userId, @RequestBody User user) {
        if (Application.role[0]==1) {
            dao.updatePasswordById(user.getPassword(), userId);
            return dao.findById(userId).get();

        }
        return null;
    }

    @GetMapping(path = "/update/increasecredit")
    public String increaseCredit(@RequestParam(name = "id") Long userId, @RequestParam float amount) {
        if (Application.role[0]==1) {
            if (dao.existsById(userId)) {
                dao.updateCreditById(dao.getById(userId).getCredit() + amount, userId);
                return "Credit updated successfully";
            } else {
                return "User not found";
            }
        }
        return null;
    }

    @PutMapping(path = "/update/assigncredit")
    public String assignCredit(@RequestParam(name = "id") Long userId, @RequestBody User user) {
        if (Application.role[0]==1) {
            if (dao.existsById(userId)) {
                dao.updateCreditById(user.getCredit(), userId);
                return "Credit updated successfully";
            } else {
                return "User not found";
            }
        }
        return null;
    }

    @GetMapping(path = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getById(@RequestParam(name = "id") Long userId) {
        if (Application.role[0]==1) {
            return dao.findById(userId).get();

        }
        return null;
    }

    @GetMapping(path = "/grantservice")//اعطای مجوز یک سرویس با استفاده از ای دی یوزر و اسم سرویس موردنظر
    String grantServiceToUser(@RequestParam(name = "userid") Long userId, @RequestParam(name = "servicename") String serviceName) {
        if (Application.role[0]==1) {
            User user = dao.findById(userId).get();
            Service service = dao2.findByServiceName(serviceName);
            UserService us = new UserService();
            us.setService(service);
            us.setUser(user);
            dao3.save(us);
            return "service granted sucessfully";
        }
        return null;

//        user.getServices().add(dao2.findByServiceName(serviceName));
//        dao.save(user);
    }

    @GetMapping(path = "/cancelservice")//لغو مجوز استفاده یک سرویس برای یوزر
    String cancelServiceForUser(@RequestParam(name = "userid") Long userId, @RequestParam(name = "servicename") String serviceName) {
        if (Application.role[0]==1) {
            dao3.deleteById(new UserServiceId(userId, dao2.findByServiceName(serviceName).getServiceId()));
            return "service cancelled successfully";
        }
        return null;
    }

    @GetMapping(path = "/grantedservices")//نمایش سرویس های مجازکاربر
    Set<Service> showGrantedServices(@RequestParam(name = "userid") Long userId) {
        if (Application.role[0]==-1 && Application.role[1]==userId) {
            Set<UserService> us = dao.findById(userId).get().getUserservices();
            Set<Service> grantServices = new HashSet<Service>();
            for (UserService userservice : us) {
                grantServices.add(userservice.getService());
            }
            return grantServices;
        }
        return null;
    }

    @GetMapping(path = "enabledservices")//نمایش سرویس های فعال کاربر
    Set<Service> showEnabledServices(@RequestParam(name = "userid") Long userId) {
        if (Application.role[0]==-1 && Application.role[1]==userId) {
            LocalDate ld = LocalDate.now();
            LocalTime lt = LocalTime.now();
            Set<Service> enables = new HashSet<Service>();
            Set<UserService> us = dao.findById(userId).get().getUserservices();
            for (UserService userservice : us) {
                Service service = userservice.getService();
                for (ServicePeriod sp : service.getPeriods()) {
                    if (sp.getStatus() == true && sp.getDate().compareTo(ld) == 0
                            && sp.getStart().compareTo(lt) <= 0 && sp.getEnd().compareTo(lt) > 0) {

                        enables.add(service);
                    } else {
                        System.out.println("sp.getDate().compareTo(ld) " + sp.getDate().compareTo(ld)
                                + " sp.getStart().compareTo(lt) " + sp.getStart().compareTo(lt) + "sp.getEnd().compareTo(lt)>0 "
                                + sp.getEnd().compareTo(lt));
                    }
                }
            }
            return enables;
        }

        return null;
    }

    @GetMapping(path = "/useservice")//to use a service(by user)
    String useAnEnabledService(@RequestParam(name = "userid") Long userId, @RequestParam(name = "serviceid") Long serviceId) {
        if (Application.role[0]==-1 && Application.role[1]==userId) {
            Set<Service> enables = showEnabledServices(userId);//call API
            LocalDate date_now;
            LocalTime time_now;
            if (!enables.isEmpty()) {
                for (Service service : enables) {
                    if (serviceId == service.getServiceId()) {
                        UserService userService = dao3.findById(new UserServiceId(userId, serviceId)).get();
                        if (userService.getUsetimes() < service.getAllowedTimes()) {//چک کردن سقف مجازتعداد دفعات استفاده سرویس برای استفاده کاربر
                            User user = dao.findById(userId).get();
                            if (user.getCredit() >= service.getServiceCost()) {
                                date_now = LocalDate.now();
                                time_now = LocalTime.now();
                                userService.setUsetimes(((userService.getUsetimes()) + 1));//update number of usage times
                                user.setCredit((user.getCredit() - service.getServiceCost()));
                                dao.save(user);
                                dao3.save(userService);
                                LogService log = new LogService();
                                log.setDate(date_now);
                                log.setTime(time_now);
                                log.setServiceName(service.getServiceName());
                                log.setServiceId(service.getServiceId());
                                log.setUser(user);
                                dao4.save(log);
                                return "You have used this service successfully!";
                            } else {
                                return "you have not enough credit for this service!";
                            }
                        } else {
                            return "You have reached the authorized limit for using this service!";
                        }
                    }
                }
            }

            return "You have no enable service to use!";
        }

        return null;
    }

    @GetMapping(path = "/userservices/report")
    Set<LogService> getUserServicesReports(@RequestParam(name = "userid") Long userId) {
        if (Application.role[0]==-1 && Application.role[1]==userId) {
            return dao.getById(userId).getLogs();

        }
        return null;
    }

}
