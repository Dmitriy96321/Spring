package org.example.config;
import org.example.env.EnvInit;
import org.example.env.EnvironmentContacts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@ComponentScan("org.example")
@Configuration
@Profile("init")
public class InitAppConfig {
    @Bean
    public EnvironmentContacts envDef(){
        return new EnvInit();
    }
}
