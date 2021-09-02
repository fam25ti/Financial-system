/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1.io.dao;

import com.mycompany.progchallenge1.io.entity.User;
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
public interface UserRepository extends JpaRepository<User, Long>{
    @Transactional
    @Modifying
    @Query("update User user set user.userName = :username where user.userId = :id")
    void updateUsernameById(@Param("username") String username, @Param("id") Long id);
    
    @Transactional
    @Modifying
    @Query("update User user set user.password = :password where user.userId = :id")
    void updatePasswordById(@Param("password") String password, @Param("id") Long id);
   
    @Transactional
    @Modifying
    @Query("update User user set user.credit = :credit where user.userId = :id")
    void updateCreditById(@Param("credit") float credit, @Param("id") Long id);

   
}
