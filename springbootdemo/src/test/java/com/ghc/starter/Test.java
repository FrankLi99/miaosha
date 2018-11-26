package com.ghc.starter;


import com.ghc.starter.redis.RedisService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@Configuration
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    private RedisService redisService;

    @org.junit.Test
    public void testRedisService(){
        redisService.set("key1","value1");
        String value = (String)redisService.get("key1",String.class);
        System.out.println("value: "+value);
    }

}
