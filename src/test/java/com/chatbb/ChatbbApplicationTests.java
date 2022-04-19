package com.chatbb;

import com.chatbb.Utils.MD5;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ChatbbApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(MD5.getMD5("123"));
    }

}
