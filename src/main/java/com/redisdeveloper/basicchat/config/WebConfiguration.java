package com.redisdeveloper.basicchat.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Serves the React build that lives under {@code src/main/resources/client/build}
 * and forwards “/” to the SPA entry-point.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /** Redirect “/” to the SPA’s index.html (React Browser-Router friendly). */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Forward instead of redirect so the original URL stays in the address bar
        registry.addViewController("/").setViewName("forward:/index.html");
    }

    /** Expose the compiled React assets on the classpath. */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")                           // everything under /
                .addResourceLocations("classpath:/client/build/")   // your React build output
                .setCacheControl(CacheControl.noCache())            // ⬅︎ match the old setCachePeriod(0)
                .resourceChain(false);                              // disable Spring’s static-resource caching
    }
}