package org.example.bikesmart;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BikesmartApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikesmartApplication.class, args);
	}
	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return objectMapper;
	}
	@Bean
	public RestTemplate restTemplate(ObjectMapper objectMapper) {
		RestTemplate restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
		messageConverter.setObjectMapper(objectMapper);
		restTemplate.getMessageConverters().removeIf(m -> m instanceof MappingJackson2HttpMessageConverter);
		restTemplate.getMessageConverters().add(messageConverter);
		return restTemplate;
	}

}
