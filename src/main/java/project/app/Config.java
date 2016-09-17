package project.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
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
//@EntityScan("project")
//@EnableJpaRepositories("project")
//@EnableTransactionManagement
@EnableWebMvc
public class Config {

}