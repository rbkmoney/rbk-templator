package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}}

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [Adapter{{properCase bank_name}}Application::class])
class Adapter{{properCase bank_name}}ApplicationTest {

    @Test
    fun contextLoads() {
    }
}
