package br.com.gabrielcunha.config.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
 
	private final String baseUrl = "http://localhost:8080/miniaturas/";
	
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        		.allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS");
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      String baseUrl = StringUtils.trimTrailingCharacter(this.baseUrl, '/');
      registry.
          addResourceHandler(baseUrl + "/swagger-ui/**")
          .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
          .resourceChain(false);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController(baseUrl + "/swagger-ui/")
          .setViewName("forward:" + baseUrl + "/swagger-ui/index.html");
    }	    
    
    
}
