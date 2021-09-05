package com.mycompany.progchallenge1.io.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
    
    @Column(name = "CREDIT", columnDefinition = "float default 0")    
    private float credit;
    
    @OneToMany(mappedBy = "_user", cascade = CascadeType.ALL)
    @JsonBackReference
    //@JsonManagedReference
    private Set<UserService> userservices;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "user",cascade={CascadeType.PERSIST,CascadeType.MERGE},orphanRemoval=true)
    private Set<LogService> logs;
    
    public Set<UserService> getUserservices() {
        return userservices;
    }
    
    public void setUserservices(Set<UserService> userservices) {
        this.userservices = userservices;
    }
    
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
    
    public Set<LogService> getLogs() {
        return logs;
    }
    
    public void setLogs(Set<LogService> logs) {
        this.logs = logs;
    }

    
    @Override
    public String toString() {
        return "User{" + "userId=" + userId + ", userName=" + userName + ", password=" + '}';
    }
    
}
