package com.anixton.jobportal.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private static final String UPLOAD_DIR = "photos";

    /**
     * Configures the resource handlers.
     *
     * @param registry the ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory(UPLOAD_DIR, registry);
    }

    /**
     * Exposes the specified directory to be accessible via the web.
     *
     * @param uploadDir the directory to expose
     * @param registry the ResourceHandlerRegistry
     */
    private void exposeDirectory(String uploadDir, @NotNull ResourceHandlerRegistry registry) {
        Path path = Paths.get(uploadDir);
        registry.addResourceHandler("/" + uploadDir + "/**")
                .addResourceLocations("file:" + path.toAbsolutePath() + "/");
    }
}
