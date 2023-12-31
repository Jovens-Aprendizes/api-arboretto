package br.com.arboretto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)).paths(PathSelectors.any())
				.build().apiInfo(metaInfo());
	}

	private ApiInfo metaInfo() {
		@SuppressWarnings("deprecation")
		ApiInfo apiInfo = new ApiInfo("API Projeto Arboretto", "API de integração Arboretto - Desenvolvimento",
				"1.0.0 - Snapshot", "", "", "", "");
		return apiInfo;
	}

}
