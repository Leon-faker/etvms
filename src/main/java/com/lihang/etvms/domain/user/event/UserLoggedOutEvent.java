package com.lihang.etvms.domain.user.event;

import org.springframework.context.ApplicationEvent;

import java.lang.invoke.MethodHandles;

/**
 * 用户退出登录事件
 *
 * @date 2023/1/5
 **/
public class UserLoggedOutEvent extends ApplicationEvent {

    /**
     * Token
     */
    private final String token;

    /**
     * 用户ID
     */
    private final Long userId;

    public UserLoggedOutEvent(String token, Long userId) {
        super(MethodHandles.lookup().lookupClass());
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public Long getUserId() {
        return userId;
    }
}
