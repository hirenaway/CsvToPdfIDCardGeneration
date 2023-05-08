package com.springboot.csvtopdf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Hiren Solanki
 *
 * @version 1.0
 */

@Configuration
@Import(SpringDataRestConfiguration.class)
public class SpringFoxConfig {
	/**
	 * @author Hiren Solanki
	 * 
	 * @see https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
	 *
	 * @return Configure and builds Swagger documentation based on the created apis and returns Docket from springfox dependecy package
	 *
	 * @since 1.0
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.springboot.csvtopdf"))
				.paths(PathSelectors.any())
				.build();
	}

}
