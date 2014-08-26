package reparaciones.utils;

import java.net.URI;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.client.Traverson;
import org.springframework.hateoas.client.Traverson.TraversalBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

public final class RestfulHalClient {

	private static RestOperations restTemplate;

	private Traverson traverson;

	private Traverson getClient() {
		return traverson;
	}

	private RestfulHalClient(String rootUriString) {
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
				Class<T> responseType) {

			return builder.toEntity(responseType);
		}

		public <T, V> ResponseEntity<V> post(T resource, Class<V> responseType) {

			return getRestTemplate()
					.postForEntity(
							builder.asLink().getHref(),
							resource,
							responseType
					);
		}

	}

	private static final RestOperations getRestTemplate() {

		if (restTemplate == null)
		{
			restTemplate = new RestTemplate();
		}

		return restTemplate;
	}

}
