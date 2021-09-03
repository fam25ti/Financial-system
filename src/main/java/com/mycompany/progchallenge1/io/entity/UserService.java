/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1.io.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

/**
 *
 * @author Mahsa
 */
@Entity
public class UserService {
    @EmbeddedId
    private UserServiceId id = new UserServiceId();
    @ManyToOne
    @MapsId("userId")
    //@JsonManagedReference

    private User _user;
 
    @ManyToOne
    @MapsId("serviceId")
    //@JsonManagedReference
    private Service _service;
    
    @Column(name = "USE_TIMES")
    private int usetimes;

    public User getUser() {
        return _user;
    }

    public Service getService() {
        return _service;
    }

    public int getUsetimes() {
        return usetimes;
    }

    public void setUser(User _user) {
        this._user = _user;
    }

    public void setService(Service _service) {
        this._service = _service;
    }

    public void setUsetimes(int usetimes) {
        this.usetimes = usetimes;
    }
    
    
 
}
