package com.rbkmoney.{{adapterPayoutPackageCase bank_name}};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Adapter{{properCase bank_name}}PayoutApplication extends SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(Adapter{{properCase bank_name}}PayoutApplication.class, args);
    }

}
