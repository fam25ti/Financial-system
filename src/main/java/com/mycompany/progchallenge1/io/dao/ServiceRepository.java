/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1.io.dao;

import com.mycompany.progchallenge1.io.entity.Service;
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
public interface ServiceRepository extends JpaRepository<Service,Long> {
    @Transactional
    @Modifying
    @Query("update Service service set service.serviceCost = :cost where service.serviceId = :id")
    void updateCostById(@Param("cost") float cost, @Param("id") Long id);
    @Transactional
    @Modifying
    @Query("update Service service set service.serviceName = :name where service.serviceId = :id")
    void updateNameById(@Param("name") String name, @Param("id") Long id);
     @Transactional
    @Modifying
    @Query("update Service service set service.allowedTimes = :times where service.serviceId = :id")
    void updateNumberOfAllowedById(@Param("times") int times, @Param("id") Long id);
     @Transactional
    @Modifying
    @Query("update Service service set service.allowedTimes = :times,service.serviceName = :name,service.serviceCost = :cost where service.serviceId = :id")
    void updateAllPropertiesById(@Param("name") String name,@Param("times") int times,@Param("cost") float cost, @Param("id") Long id);
    @Transactional
    long deleteByServiceName(String name);
    @Transactional
    Service findByServiceName(String name);
    @Transactional
    boolean existsServiceByServiceName(String name);
   
       

}
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.mycompany.progchallenge1.io.dao;
//
//import com.mycompany.progchallenge1.io.entity.Service;
//import javax.transaction.Transactional;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
///**
// *
// * @author Mahsa
// */
//@Repository
//public interface ServiceRepository extends JpaRepository<Service, Long>{
//    @Transactional
//    @Modifying
//    @Query("update Service service set service.serviceName = :servicename where service.serviceId = :id")
//    void updateServiceNameById(@Param("servicename") String servicename, @Param("id") Long id);
//    
////    @Transactional
////    @Modifying
////    @Query("update User user set user.password = :password where user.userId = :id")
////    void updatePasswordById(@Param("password") String password, @Param("id") Long id);
////   
////    @Transactional
////    @Modifying
////    @Query("update User user set user.credit = :credit where user.userId = :id")
////    void updateCreditById(@Param("credit") float credit, @Param("id") Long id);
////
////   
//}
