package com.rbkmoney.{{adapterPayoutPackageCase bank_name}};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class {{properCase bank_name}}Application extends SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run({{properCase bank_name}}Application.class, args);
    }

}
