package com.rbkmoney.{{adapterPayoutPackageCase bank_name}}

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@ServletComponentScan
@SpringBootApplication
class Adapter{{properCase bank_name}}PayoutApplication: SpringApplication()

fun main(args: Array<String>) {
    runApplication<Adapter{{properCase bank_name}}PayoutApplication>(*args)
}
