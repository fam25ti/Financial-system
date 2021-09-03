///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.progchallenge1.io.entity;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import java.io.Serializable;
//import java.util.Set;
//import javax.persistence.Basic;
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
//
///**
// *
// * @author Mahsa
// */
//@Entity
//public class User implements Serializable{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "USER_ID")
//    @Basic(optional = false)
//    private Long userId;
//    
//    @Column(name = "USER_NAME", unique = true, length = 50, nullable = false)
//    private String userName;
//    
//    @Column(name = "PASS_WORD", length = 50, nullable = false)
//    private String password;
//    
//    @Column(name = "CREDIT",columnDefinition = "float default 0")    
//    private float credit;
//    
//
////    @ManyToMany(mappedBy = "users")
////    @JsonBackReference
//    @ManyToMany(cascade = { CascadeType.PERSIST })
//    @JsonManagedReference
//    @JoinTable(
//        name = "USER_SERVICE", 
//        joinColumns = { @JoinColumn(name = "USER_ID") }, 
//        inverseJoinColumns = { @JoinColumn(name = "SERVICE_ID") })
//    Set<Service> services;
//
//    public Long getUserId() {
//        return userId;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public float getCredit() {
//        return credit;
//    }
//
//    public Set<Service> getServices() {
//        return services;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public void setCredit(float credit) {
//        this.credit = credit;
//    }
//
//    public void setServices(Set<Service> services) {
//        this.services = services;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" + "userId=" + userId + ", userName=" + userName + ", password=" + password + ", credit=" + credit + ", services=" + services + '}';
//    }
//    
//    
//    
//    
//    
//}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1.io.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.HashSet;
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

/**
 *
 * @author Mahsa
 */
@Entity

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    @Basic(optional = false)
    private Long userId;
    
    @Column(name = "USER_NAME", unique = true, length = 50, nullable = false)
    private String userName;
    
    @Column(name = "PASS_WORD", length = 50, nullable = false)
    private String password;
    
    @Column(name = "CREDIT",columnDefinition = "float default 0")    
    private float credit;
    
    @ManyToMany(cascade = { CascadeType.PERSIST})
   // @JsonManagedReference
    @JoinTable(
        joinColumns = { @JoinColumn(name = "USER_ID") }, 
        inverseJoinColumns = { @JoinColumn(name = "SERVICE_ID") })
    Set<Service> services;

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public float getCredit() {
        return credit;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCredit(float credit) {
        this.credit = credit;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", password=" + password + ", credit=" + credit + ", services=" + services + '}';
    }
    
    
    
    
    
}
