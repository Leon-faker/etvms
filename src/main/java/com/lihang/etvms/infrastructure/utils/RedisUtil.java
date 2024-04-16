package com.lihang.etvms.infrastructure.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * Redis 工具类
 *
 * @date 2023/1/4
 **/
public class RedisUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final long EXPIRED_TIME = 60 * 60 * 24;

    private static RedisTemplate<String, String> redisTemplate;

    private RedisUtil() {

    }

    public static void initialize(RedisTemplate<String, String> _redisTemplate) {
        if (null == _redisTemplate) {
            LOGGER.warn("[Redis] - Uninitialized, Redis template must not be null");
            return;
        }
        redisTemplate = _redisTemplate;
        LOGGER.info("[Redis] - Initialized");
    }

    public static String wrapKey(String keyPrefix, String keyValue) {
        return keyPrefix + keyValue;
    }

    public static void add(String key, String value) {
        add(key, value, EXPIRED_TIME, ChronoUnit.SECONDS);
    }

    public static void add(String key, String value, long expired, TemporalUnit expiredTimeUnit) {
        if (null == redisTemplate) {
            LOGGER.warn("[Redis] - Redis template is null");
            return;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[Redis] - action: add, key: {}, value: {}, expired: {}-{}", key, value, expired, expiredTimeUnit);
        }
        Duration duration = Duration.of(expired, expiredTimeUnit);
        redisTemplate.opsForValue().set(key, value, duration);
    }

    public static String get(String key) {
        if (null == redisTemplate) {
            LOGGER.warn("[Redis] - Redis template is null");
            return null;
        }
        String result = redisTemplate.opsForValue().get(key);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[Redis] - action: get, key: {}, result: {}", key, result);
        }
        return result;
    }

    public static boolean isExists(String key) {
        String value = get(key);
        return null != value && !"".equals(value);
    }

    public static void remove(String key) {
        if (null == redisTemplate) {
            LOGGER.warn("[Redis] - Redis template is null");
            return;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("[Redis] - action: delete, key: {}", key);
        }
        redisTemplate.delete(key);
    }
}
