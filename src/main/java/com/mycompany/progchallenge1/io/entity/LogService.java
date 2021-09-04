/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1.io.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Mahsa
 */
@Entity
public class LogService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOG_ID")
    @Basic(optional = false)
    private Long logId;
    
    @Column(name = "USAGE_TIME", columnDefinition = "TIME")
    private LocalTime time;
    
    @Column(name = "USAGE_DATE", columnDefinition = "DATE")
    private LocalDate date;
    
    @Column(name = "SERVICE_NAME",length = 300)
    private String serviceName;
    
    @Column(name = "SERVICE_ID")
    private Long serviceId;
    @ManyToOne
   @JsonBackReference
    @JoinColumn(name="USER_ID", nullable = true)
    private User user;
    
    public Long getLogId() {
        return logId;
    }

    public LocalTime getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getServiceName() {
        return serviceName;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public User getUser() {
        return user;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
