/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1.io.dao;

import com.mycompany.progchallenge1.io.entity.LogService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mahsa
 */
@Repository
public interface LogServiceRepository extends JpaRepository<LogService, Long>{
    
    
}
