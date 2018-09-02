package ar.edu.unq.epers.bichomon.frontend.app;

import ar.edu.unq.epers.bichomon.frontend.api.EspecieServiceREST;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Esto es codigo de frontend, no deberian tocar nada de aca.
 */
@ApplicationPath("api")
public class BichomonWebApp extends ResourceConfig {

	public BichomonWebApp() {
		this.packages(true, EspecieServiceREST.class.getPackage().getName())
			.register(JsonObjectMapperProvider.class)
	        .register(JacksonFeature.class);
	}
	
}
