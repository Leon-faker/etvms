package com.lihang.etvms.infrastructure.utils;


import org.springframework.lang.Nullable;

/**
 * 用户上下文
 *
 * @date 2022/12/16
 **/
public class UserSecurityContext {

    private static final ThreadLocal<Long> USER_THREAD_LOCAL = new ThreadLocal<>();

    private UserSecurityContext() {

    }

    @Nullable
    public static Long get() {
        return USER_THREAD_LOCAL.get();
    }

    public static void put(Long user) {
        if (null == user) {
            return;
        }
        USER_THREAD_LOCAL.set(user);
    }
}
