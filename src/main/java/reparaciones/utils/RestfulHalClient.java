package reparaciones.utils;

import java.net.URI;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.ResponseEntity;

public final class RestfulHalClient {

	private Traverson traverson;
	
	private Traverson getClient() {
		return traverson;
	}
	
	public RestfulHalClient(String rootUriString) {
		this.traverson = new Traverson(
				 URI.create(rootUriString),
				MediaTypes.HAL_JSON);
	}

	public static RestfulHalClient newInstance(String rootUriString) {
		return new RestfulHalClient(rootUriString);
	}

	
	public <T extends ResourceSupport>  ResponseEntity<T> toEntity(String path, Class<T> type) {

		return getClient().follow(path).toEntity(type);
	}
	
}
