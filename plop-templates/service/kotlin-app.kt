package com.rbkmoney.{{packageCase name}}

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@ServletComponentScan
@SpringBootApplication
class {{properCase name}}Application: SpringApplication()

fun main(args: Array<String>) {
    runApplication<{{properCase name}}Application>(*args)
}
