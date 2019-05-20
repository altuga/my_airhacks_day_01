/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.ping.boundary;

import java.util.List;
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
        
        return "Hello from hotel" ;
    }
    
    public List<Ping> pingAll() {
        return entityManager.createNamedQuery("all", Ping.class).getResultList();
    }
    
    
    public void save(Ping ping) {
        entityManager.merge(ping);
    }
    
    
}
