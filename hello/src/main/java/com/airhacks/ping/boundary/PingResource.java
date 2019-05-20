package com.airhacks.ping.boundary;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author airhacks.com
 */
@Path("ping")
public class PingResource {
    
    @Inject
    Pingy pingy ;

    @GET
    public Ping ping() {
        //return "Enjoy Java EE 8!";
        
        return new Ping(pingy.pingMe() , pingy.getClass().getName() );
    }
    
    @GET
    @Path("all")
    public List<Ping> getAll() {
        return pingy.pingAll();
    }
    
    @POST
    public void save(Ping ping) {
        pingy.save(ping);
    }

}
