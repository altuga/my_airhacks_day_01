/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.ping.boundary;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author altuga
 */
@Entity
@NamedQuery(name = "all" , query = "select ping from Ping ping")
public class Ping {
    
    @JsonbProperty("hoppa")
    public String name;
    public String proxyClass;
    
    @Id
    @GeneratedValue
    public long id; 

    public Ping(String name, String proxyClass) {
        this.name = name;
        this.proxyClass = proxyClass;
    }

    public Ping() {
    }
    
    
    
    
    
    
    
}
