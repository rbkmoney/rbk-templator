package com.rbkmoney.{{packageCase name}};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class {{properCase name}}Application extends SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run({{properCase name}}Application.class, args);
    }

}
