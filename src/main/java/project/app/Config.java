package project.app;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/**
 * Created by Green-L on 11.09.2016.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan("project")
@PropertySource("classpath:application.properties")
@EnableWebMvc
public class Config {

}