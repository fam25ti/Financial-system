/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1.io.entity;

import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Mahsa
 */
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SERVICE_ID")
    @Basic(optional = false)
    private Long serviceId;
    
    @Column(name = "SERVICE_NAME",unique = true, length = 300)
    private String serviceName;
    
    @Column(name = "SERVICE_COST", columnDefinition = "float default 10")
    private float serviceCost;
    
    @Column(name = "ALLOWED_TIMES")
    private int allowedTimes;
    
//    @Column(name = "SERVICE_STATUS", columnDefinition = "boolean default false")    
//    private boolean serviceStatus;
    
    @ManyToMany(mappedBy = "services")
    private Set<User> users;
    
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    Set<ServicePeriod> periods;

    public Long getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public float getServiceCost() {
        return serviceCost;
    }

    public int getAllowedTimes() {
        return allowedTimes;
    }

//    public boolean isServiceStatus() {
//        return serviceStatus;
//    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<ServicePeriod> getPeriods() {
        return periods;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceCost(float serviceCost) {
        this.serviceCost = serviceCost;
    }

    public void setAllowedTimes(int allowedTimes) {
        this.allowedTimes = allowedTimes;
    }

//    public void setServiceStatus(boolean serviceStatus) {
//        this.serviceStatus = serviceStatus;
//    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void setPeriods(Set<ServicePeriod> periods) {
        this.periods = periods;
    }

    @Override
    public String toString() {
        return "Service{" + "serviceId=" + serviceId + ", serviceName=" + serviceName + ", serviceCost=" + serviceCost + ", allowedTimes=" + allowedTimes + ", users=" + users + ", periods=" + periods + '}';
    }
   
    
    
    
    
}
