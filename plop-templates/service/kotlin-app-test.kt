package com.rbkmoney.{{packageCase name}}

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [{{properCase name}}Application::class])
class {{properCase name}}ApplicationTests {

    @Test
    fun contextLoads() {
    }

}
