package com.bontech.intershipt.demo.service;


import com.bontech.intershipt.demo.io.dao.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ServiceService  {
    @Autowired
    private DepartmentRepository dao;




}
