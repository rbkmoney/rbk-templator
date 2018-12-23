package com.rbkmoney.{{lowerCase name}};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class {{sentenceCase name}}Application extends SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run({{sentenceCase name}}Application.class);
    }

}
