package com.zkl.notionpageservice;

import com.zkl.notionpageservice.notion.config.NotionConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties(NotionConfigProperties.class)
public class NotionPageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotionPageServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
