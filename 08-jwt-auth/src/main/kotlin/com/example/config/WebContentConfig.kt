package com.example.config

import com.example.serializationConverters.yaml.YamlContentConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebContentConfig : WebMvcConfigurer {

    private val APPLICATION_YAML = MediaType.valueOf("application/x-yaml")

    @Value("\${cors.originPatterns:default}")
    private val corsOriginPatterns: String = "";

    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(YamlContentConverter())
    }

    override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {
        configurer.favorParameter(false)
            .ignoreAcceptHeader(false)
            .useRegisteredExtensionsOnly(false)
            .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("x-yaml", APPLICATION_YAML)
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        val allowedOrigins = corsOriginPatterns.split(",").toTypedArray();

        registry.addMapping("/**")
            .allowedMethods("*")
            .allowedOrigins(*allowedOrigins)
            .allowCredentials(true);
    }
}