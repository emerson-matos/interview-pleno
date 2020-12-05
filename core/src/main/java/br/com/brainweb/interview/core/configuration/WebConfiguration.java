package br.com.brainweb.interview.core.configuration;

import javax.annotation.PostConstruct;
import java.time.ZoneOffset;
import java.util.TimeZone;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @PostConstruct
    void init() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));
    }
}