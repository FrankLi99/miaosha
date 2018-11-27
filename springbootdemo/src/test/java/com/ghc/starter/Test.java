package com.ghc.starter;


import com.ghc.starter.domain.model.User;
import com.ghc.starter.prefix.UserKey;
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
        User user = new User();
        user.setId(2);
        user.setName("frank2");

        redisService.set(UserKey.getById,"1",user);
        User getUser = (User)redisService.get(UserKey.getById,"1",User.class);

        System.out.println(getUser.getName());
    }

}
