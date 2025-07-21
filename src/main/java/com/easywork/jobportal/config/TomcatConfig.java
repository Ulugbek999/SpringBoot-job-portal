package com.easywork.jobportal.config;



import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.coyote.http11.AbstractHttp11Protocol;

@Configuration
public class TomcatConfig {

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> containerCustomizer() {
        return factory -> factory.addConnectorCustomizers(connector -> {
            if (connector.getProtocolHandler() instanceof AbstractHttp11Protocol<?> protocolHandler) {
                // This controls how many individual file parts are allowed in a multipart request
                protocolHandler.setMaxSwallowSize(50 * 1024 * 1024); // 50MB
                connector.setProperty("maxFileCount", "5"); // ← this is the workaround for Tomcat’s limit
            }
        });
    }
}
