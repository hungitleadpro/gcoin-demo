package com.feature.gcoin.config;

import com.feature.gcoin.common.exceptionhandling.HandlerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TienNV
 * @CreatedDate Oct 4, 2017 2:50:17 PM
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	HandlerRequest handlerRequest;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(handlerRequest);
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(byteArrayHttpMessageConverter());
	}

	@Bean
	public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
		ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
		arrayHttpMessageConverter.setSupportedMediaTypes(getSupportedMediaTypes());
		return arrayHttpMessageConverter;
	}

	private List<MediaType> getSupportedMediaTypes() {
		List<MediaType> list = new ArrayList<MediaType>();
		list.add(MediaType.IMAGE_JPEG);
		list.add(MediaType.IMAGE_PNG);
		list.add(MediaType.APPLICATION_OCTET_STREAM);
		list.add(MediaType.APPLICATION_PDF);
		return list;
	}

//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		String document = propertiesUtils.getFileDirectory();
//		super.addResourceHandlers(registry);
//		if (!document.endsWith("/")) {
//			document += "/";
//		}
//		registry.addResourceHandler("/private/**").addResourceLocations("file://" + document).setCachePeriod(5);
//	}

}
