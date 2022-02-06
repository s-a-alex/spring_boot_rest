package getphos.spring.spring_boot_rest.test;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {

	@Autowired ApplicationContext ctx;

	@Bean Credentials credentials(){
		return new UsernamePasswordCredentials("user", "user");
	}

	@Bean
	CredentialsProvider provider() {
		BasicCredentialsProvider provider = new BasicCredentialsProvider();
		provider.setCredentials(AuthScope.ANY, credentials());
		return provider;
	}

	@Bean
	public HttpComponentsClientHttpRequestFactory factory() {
		return new HttpComponentsClientHttpRequestFactory(HttpClients.custom().setDefaultCredentialsProvider(provider()).build());
	}

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(factory());
		return restTemplate;
	}
}
