package com.mycompany.progchallenge1.service.service;


import com.mycompany.progchallenge1.io.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TheUserService  {
    @Autowired
    private UserRepository dao;




}