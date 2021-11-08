package com.rbkmoney.{{adapterAcquiringPackageCase bank_name}};

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Adapter{{properCase bank_name}}Application.class)
public class Adapter{{properCase bank_name}}ApplicationTest {

    @Test
    public void contextLoads() {

    }
}
