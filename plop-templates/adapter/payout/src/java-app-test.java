package com.rbkmoney.{{adapterPayoutPackageCase bank_name}};

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Adapter{{properCase bank_name}}PayoutApplication.class)
public class Adapter{{properCase bank_name}}PayoutApplicationTest {

    @Test
    public void contextLoads() {

    }
}
