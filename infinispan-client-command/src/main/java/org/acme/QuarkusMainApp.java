package org.acme;

import javax.inject.Inject;

import org.acme.infinispan.client.InfinispanClientApp;
import org.acme.infinispan.client.InfinispanGreetingResource;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
//This is test comment

@QuarkusMain
public class QuarkusMainApp implements QuarkusApplication{
	
	@Inject
	public InfinispanGreetingResource testobj;
	 
	@Inject
	public InfinispanClientApp testclient;
	
	@Override
	public int run(String... args) throws Exception {
		System.out.println("Hello world");
		testclient.onStart();
		System.out.println(testobj.getvalue("hello"));
		System.out.println(testobj.putvalue("key1","value1"));
		System.out.println(testobj.getvalue("key2"));
		return 0;
	}

}
