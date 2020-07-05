package com.europeana.test.assessment.config;

import com.europeana.test.assessment.model.ModelStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Configuration class to get the object of ModelStore through the application.
 */
@Configuration
public class ModelStoreConfig {
	@Bean
	public ModelStore modelStore() {
		return new ModelStore();
	}

}
