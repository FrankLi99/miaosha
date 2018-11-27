package com.ghc.starter.redis;

import com.alibaba.fastjson.JSON;
import com.ghc.starter.prefix.KeyPrefix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService <T> {
    @Autowired
    private JedisPool jedisPool;

    public T get(KeyPrefix keyPrefix,String key, Class<T> clazz){
        Jedis jedis = null;
        T t;
        try{jedis = jedisPool.getResource();
            String realKey = keyPrefix.getPrefix() + key;
            String value = jedis.get(realKey);
            t = str2Bean(value,clazz);
        }finally{
            return2Pool(jedis);
        }
        return t;
    }
    public boolean set(KeyPrefix keyPrefix,String key,T value){
        Jedis jedis = null;
        try{jedis = jedisPool.getResource();
            String str = bean2Str(value);
            if(str==null||str.length()<=0){
                return false;
                }else{
                         String realKey = keyPrefix.getPrefix() + key;
                         int expireSeconds = keyPrefix.expireSeconds();
                         if(expireSeconds>0){
                             jedis.setex(realKey,expireSeconds,str);
                         }
                         else{
                                jedis.set(realKey,str);
                         }
                 return true;
                }
        }finally{
            return2Pool(jedis);
        }
    }
    public boolean exists(KeyPrefix keyPrefix,String key){
        Jedis jedis = null;
        try{    jedis = jedisPool.getResource();
                String realKey = keyPrefix.getPrefix() + key;
                return jedis.exists(realKey);
        }finally{
            return2Pool(jedis);
        }
    }
    @SuppressWarnings("unchecked")
    private String bean2Str(T value){
        Class<?> clazz = value.getClass();
        if(value == null){
            return null;
        }
        else if(clazz == int.class||clazz == Integer.class){
           return ""+value;
        }else if(clazz == String.class){
            return (String)value;
        }else if(clazz == long.class||clazz == Long.class){
            return ""+value;
        }else{
            return JSON.toJSONString(value);
        }
    }
    @SuppressWarnings("unchecked")
    private T str2Bean(String value, Class<T> clazz) {
        if(value==null||value.length()<=0||clazz==null){
            return null;
        }else if(clazz==int.class||clazz == Integer.class){
            return (T)Integer.valueOf(value);
        }else if(clazz==long.class||clazz == Long.class){
            return (T)Long.valueOf(value);
        }else if(clazz==String.class){
            return (T)value;
        }else{ return JSON.toJavaObject(JSON.parseObject(value),clazz);}
    }

    private void return2Pool(Jedis jedis) {
        if(jedis!=null){
            jedis.close();
        }
    }
}
