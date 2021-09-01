/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1.io.entity;

import java.time.LocalDate;
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

public class ServicePeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERIOD_ID")
    @Basic(optional = false)
    private Long periodId;
    
    @Column(name = "START_TIME")
    @Temporal(TemporalType.TIME)
    private Date start;  
    
    @Column(name = "END_TIME")
    @Temporal(TemporalType.TIME)
    private Date end;
    
    @Column(name = "DAY_DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @ManyToOne()
    @JoinColumn(name="SERVICE_ID", nullable=false)
    private Service service;
    
    public Long getPeriodId() {
        return periodId;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public Date getDate() {
        return date;
    }

    public void setPeriodId(Long periodId) {
        this.periodId = periodId;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "ServicePeriod{" + "periodId=" + periodId + ", start=" + start + ", end=" + end + ", date=" + date + ", service=" + service + '}';
    }

  
    
}
