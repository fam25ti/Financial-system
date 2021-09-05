/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1.io.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Mahsa
 */
@Entity

public class ServicePeriod implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERIOD_ID")
    @Basic(optional = false)
    private Long periodId;
    
    @Column(name = "START_TIME",columnDefinition = "TIME")

    private LocalTime start;  
    
    @Column(name = "END_TIME", columnDefinition = "TIME")
    private LocalTime end;
    
    @Column(name = "DAY_DATE", columnDefinition = "DATE")
    private LocalDate  date;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="SERVICE_ID", nullable=false)
    private Service service;
    
    @Column(name = "STATUS")
    private boolean  status;
    
    public Long getPeriodId() {
        return periodId;
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public void setDate(LocalDate date) {
        
        this.date = date;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ServicePeriod{" + "periodId=" + periodId + ", start=" + start + ", end=" + end + ", date=" + date + ", service=" + service + ", status=" + status + '}';
    }

    


  
    
}
