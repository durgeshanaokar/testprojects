package org.acme.infinispan.client;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.infinispan.client.hotrod.RemoteCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.quarkus.infinispan.client.Remote;

@ApplicationScoped
public class InfinispanGreetingResource {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger("InfinispanGreetingResource");

    @Inject
    @Remote("mycache")
    RemoteCache<String, String> cache;

    public String getvalue(String key) {
    	LOGGER.info("Inside getvalue key "+key);
    	String value = cache.get(key);
    	LOGGER.info("The fetched key is : "+value);
    	
    	return value;
    }
    
    public String putvalue( String key,  String value) {
    	LOGGER.info("Inside putvalue");
    	LOGGER.info("The entered Key is "+key+" and value is "+value);
        System.out.println(cache.put(key,value));
        return "Cache key "+key+ " value "+value;
    }
    
}
