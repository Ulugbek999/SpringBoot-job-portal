package com.easywork.jobportal.config;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



//A configuration class to map requests for /photos to serve files from a directory
//on the file system

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private static final String UPLOAD_DIR = "photos";

    //Overriding the default implementation to set up a custom resource handler.
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){

       exposeDirectory(UPLOAD_DIR, registry);

    }

    private void exposeDirectory(String uploadDir, ResourceHandlerRegistry registry){
        Path path = Paths.get(uploadDir);

        //Converting the uploadDir string to a Path
        //Mapping requests starting with "/photos/**" to a file system location ->
        // -> <absolute path to photos directory>

        //the ** will match on all sub-directories

        registry.addResourceHandler("/" + uploadDir + "/**")
                .addResourceLocations("file:" + path.toAbsolutePath() + "/");
        
    }


}
