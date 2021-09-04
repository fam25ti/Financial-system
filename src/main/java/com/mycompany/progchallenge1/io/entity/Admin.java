/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1.io.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 * @author Mahsa
 */
@Entity
public class Admin implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADMIN_ID")
    @Basic(optional = false)
    private Long adminId;
    
    @Column(name = "USER_NAME",unique = true, length = 50, nullable = false)
    private String userName;
    
    @Column(name = "PASS_WORD", length = 50, nullable = false)
    private String password; 
    
    //@Transient private String message;

    public Long getAdminId() {
        return adminId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//    
    @Override
    public String toString() {
        return "Admin{" + "adminId=" + adminId + ", userName=" + userName + ", password=" + password + '}';
    }
    
}
