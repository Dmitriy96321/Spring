package org.example.config;

import org.example.env.EnvDef;
import org.example.env.EnvironmentContacts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@ComponentScan("org.example")
@Configuration
@Profile("default")

public class DefAppConfig {
    @Bean
    public EnvironmentContacts envDef(){
        return new EnvDef();
    }

}