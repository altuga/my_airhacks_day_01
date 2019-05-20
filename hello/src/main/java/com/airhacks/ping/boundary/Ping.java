/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.airhacks.ping.boundary;

import javax.json.bind.annotation.JsonbProperty;

/**
 *
 * @author altuga
 */
public class Ping {
    
    @JsonbProperty("hoppa")
    public String name;
    public String proxyClass;

    public Ping(String name, String proxyClass) {
        this.name = name;
        this.proxyClass = proxyClass;
    }
    
    
    
    
    
}
