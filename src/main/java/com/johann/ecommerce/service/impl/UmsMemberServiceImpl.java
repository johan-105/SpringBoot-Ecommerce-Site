package com.johann.ecommerce.service.impl;

import com.johann.ecommerce.service.RedisService;
import com.johann.ecommerce.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    private final RedisService redisService;

    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    @Autowired
    public UmsMemberServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public String generateAuthCode(String phone) {
        StringBuilder stringBuilder = new StringBuilder();

        int LOOP_TIME = 6;
        for (int i = 0; i < LOOP_TIME; i++) {
            stringBuilder.append(new Random().nextInt(10));
        }
        // combine the authCode and the phone number
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + phone, stringBuilder.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + phone, AUTH_CODE_EXPIRE_SECONDS);
        return stringBuilder.toString();
    }

    @Override
    public Boolean verifyAuthCode(String phone, String authCode) {
        if (authCode.isEmpty()) {
            return false;
        }
        String trueAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + phone);
        return trueAuthCode.equals(authCode);
    }
}
