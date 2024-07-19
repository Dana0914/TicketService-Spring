package kz.runtime.ticketservicespring.config;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableConfigurationProperties
@PropertySource("classpath:application.properties")
@ComponentScan("kz.runtime.ticketservicespring")
public class MyCustomConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "conditional", name = "bean", havingValue = "true")
    public String thisIsMyFirstConditionalBean() {
        return "ThisIsMyFirstConditionalBean";
    }
}
