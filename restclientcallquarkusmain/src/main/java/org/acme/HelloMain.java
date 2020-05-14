package org.acme;

import java.awt.Desktop;
import java.net.URI;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class HelloMain implements QuarkusApplication {

	@ConfigProperty(name = "quarkus.http.port")
	Integer assignedPort;

	public static void main(String[] args) {
		Quarkus.run(HelloMain.class, args);
	}

	@Override
	public int run(String... args) throws Exception {
		URI webappUri = new URI("http://localhost:" + assignedPort + "/index.html");

		Desktop.getDesktop().browse(webappUri);
		Quarkus.waitForExit();

		return 0;
	}
}
