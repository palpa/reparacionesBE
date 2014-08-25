package reparaciones.utils;

import java.net.URI;

import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.client.Traverson.TraversalBuilder;
import org.springframework.http.ResponseEntity;

import reparaciones.resources.CustomerResource;

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

	public RestfulTraversalBuilder follow(String... rels) {
		return new RestfulTraversalBuilder(getClient().follow(rels));
	}

	public class RestfulTraversalBuilder {

		private TraversalBuilder builder;

		private RestfulTraversalBuilder(TraversalBuilder builder) {
			this.builder = builder;
		}

		public <T extends ResourceSupport> ResponseEntity<T> toEntity(
				Class<T> type) {

			return builder.toEntity(type);
		}

		public ResponseEntity<Object> post(CustomerResource customerResource) {

			return null;
		}

	}

}
