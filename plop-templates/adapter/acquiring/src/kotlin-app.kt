package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@ConfigurationPropertiesScan
@ServletComponentScan
@SpringBootApplication
class Adapter{{properCase bank_name}}Application : SpringApplication()

fun main(args: Array<String>) {
    runApplication<Adapter{{properCase bank_name}}Application>(*args)
}
