package config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = { "mainpage", "detail" })
@Import({DBConfig.class, PropertyConfig.class})
public class ApplicationConfig {

}
