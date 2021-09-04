/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1.io.dao;

import com.mycompany.progchallenge1.io.entity.UserService;
import com.mycompany.progchallenge1.io.entity.UserServiceId;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mahsa
 */
@Repository
public interface UserServiceRepository extends JpaRepository<UserService,UserServiceId>{
    @Transactional
    @Modifying
    @Query("delete from UserService userservice where userservice._service.serviceId=:id")
    void deleteUserService(@Param("id") Long id);
    
}
