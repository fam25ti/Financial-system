/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.progchallenge1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Mahsa
 */
@SpringBootApplication
public class Application {
    public static long[] role=new long[2];

    public static void main(String args[]){
    role[0]=0;
    role[1]=0;
    SpringApplication.run(Application.class, args);

       // System.out.println("hello");
    
    }

}
        