/*
 * ApiWebConfig.java
 * Copyright (c) UEG.
 *
 *
 *
 *
 */
package br.ueg.musica.api.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Classe de configuração referente aos recursos Web MVC da aplicação.
 * 
 * @author UEG
 */
public class ApiWebConfig {

	public ApiWebConfig() {
	}

	@Bean
	public FilterRegistrationBean corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", this.getCorsConfiguration());
		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source), new ServletRegistrationBean[0]);
		bean.setOrder(0);
		return bean;
	}

	public CorsConfiguration getCorsConfiguration() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(false);
		config.addAllowedOriginPattern("http://*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		return config;
	}
}
