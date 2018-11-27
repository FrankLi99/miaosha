package com.ghc.starter.prefix;

public abstract class BaseKeyPrefix implements KeyPrefix {

    BaseKeyPrefix(String prefix){
        this(0,prefix);
    }
    private BaseKeyPrefix(int expireSeconds,String prefix){
        this.expireSeconds = expireSeconds;
        this.prefix = prefix;
    }
    private String prefix;
    private int expireSeconds;
    @Override
    public int expireSeconds() {  // 默认 0 代表永不过期
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        return getClass().getSimpleName()+":"+prefix;
    }
}
