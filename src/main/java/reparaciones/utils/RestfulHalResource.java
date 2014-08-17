package reparaciones.utils;

import java.net.URI;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.client.Traverson;

public final class RestfulHalResource {

	private Traverson traverson;
	
	private Traverson getClient() {
		return traverson;
	}
	
	public RestfulHalResource(String rootUriString) {
		this.traverson = new Traverson(
				 URI.create(rootUriString),
				MediaTypes.HAL_JSON);
	}

	public static RestfulHalResource newInstance(String rootUriString) {
		return new RestfulHalResource(rootUriString);
	}

	public <T extends ResourceSupport> T follow(ParameterizedTypeReference<T> typeReference, String path) {

		return getClient().follow(path).toObject(typeReference);
	}

	
}
