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

/**
 *
 * @author altuga
 */
@Stateless
@Interceptors(CallTracer.class)
public class Pingy {
    
    @PostConstruct
    public void init() {
        System.out.println(" initilizaze...");
    }
    
    
    public String pingMe() {
        return "Hello from hotel" ;
    }
    
    
}
