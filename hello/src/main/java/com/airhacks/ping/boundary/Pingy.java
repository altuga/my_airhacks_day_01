/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.ping.boundary;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.interceptor.Interceptor;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author altuga
 */
@Stateless
@Interceptors(CallTracer.class)
public class Pingy {
    
    
    @PersistenceContext
    EntityManager entityManager;
            
            
    @PostConstruct
    public void init() {
        System.out.println(" initilizaze...");
    }
    
    
    public String pingMe() {
        entityManager.merge(new Ping("Hello" , "I don't know"));
        return "Hello from hotel" ;
    }
    
    
}
