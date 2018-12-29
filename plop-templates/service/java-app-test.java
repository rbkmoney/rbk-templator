package com.rbkmoney.{{lowerCase name}};

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {{sentenceCase name}}Application.class)
public class {{sentenceCase name}}ApplicationTest {

    @Test
    public void contextLoads() {

    }
}
