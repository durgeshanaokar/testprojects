package org.acme.infinispan.client;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.infinispan.client.hotrod.RemoteCache;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.infinispan.client.Remote;

@Path("/infinispan")
public class InfinispanGreetingResource {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger("InfinispanGreetingResource");

    @Inject
    @Remote("mycache")
    RemoteCache<String, String> cache;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
    	LOGGER.info("Inside hello");
        return cache.get("hola");
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("getvalue/{key}")
    public String getvalue(@PathParam String key) {
    	LOGGER.info("Inside getvalue key "+key);
    	String value = cache.get(key);
    	LOGGER.info("The fetched key is : "+value);
    	
    	return value!=null?value:"No value for the key "+ key;
    }
    
    @POST
    @Path("/key/{key}/value/{value}")
    public String putvalue(@PathParam String key, @PathParam String value) {
    	LOGGER.info("Inside putvalue");
    	LOGGER.info("The entered Key is "+key+" and value is "+value);
        cache.put(key,value);
        return "Cache key "+key+ " value "+value;
    }
    
}
