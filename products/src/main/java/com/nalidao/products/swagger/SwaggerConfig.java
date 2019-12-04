package com.nalidao.products.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nalidao.products.controller.dto.ProductDto;
import com.nalidao.products.domain.Product;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket ApiDoc() {
		return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.nalidao.products"))
					.paths(PathSelectors.ant("/**"))
					.build()
					.apiInfo(this.productApiInfo());
	}
	
	private ApiInfo productApiInfo() {
		return new ApiInfoBuilder()
					.title("Product API")
					.description("API for product management.")
					.version("version 1.0.0")
					.contact(new Contact("PHCorrea", "https://github.com/TovarishPH", "phcorreacontato@gmail.com"))
					.build();
	}
}
