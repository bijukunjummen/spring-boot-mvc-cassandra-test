package mvctest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableEurekaClient
@EnableConfigurationProperties
public class SampleWebApplication  extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(SampleWebApplication.class)
				.run(args);
	}

	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		application.sources(SampleWebApplication.class);
		return application;
	}

}
