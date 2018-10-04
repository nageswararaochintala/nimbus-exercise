package anthem.nimbus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.*;

/**
 *
 * @author Nageswara rao
 */
@Configuration
public class SpringMvcConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurerAdapter() {
           
            @Override
            public void configurePathMatch(PathMatchConfigurer configurer) {
                configurer
                        .setUseSuffixPatternMatch(false)
                        .setUseTrailingSlashMatch(true);
            }

            @Override
            public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
                configurer
                        .favorPathExtension(false)
                        .favorParameter(true)
                        .parameterName("data-format")
                        .ignoreAcceptHeader(true)
                        .mediaType("json", MediaType.APPLICATION_JSON_UTF8)
                        .mediaType("html", MediaType.valueOf("text/html;charset=UTF-8"));
            }

            @Override
            public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
                configurer.enable();
            }

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowCredentials(false)
                        .allowedOrigins("*")
                        .allowedHeaders("*")
                        .allowedMethods("GET", "POST", "HEAD", "PUT", "DELETE");
            }

        };
    }

}