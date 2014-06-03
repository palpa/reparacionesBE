package reparaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableHypermediaSupport(type = { EnableHypermediaSupport.HypermediaType.HAL })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
