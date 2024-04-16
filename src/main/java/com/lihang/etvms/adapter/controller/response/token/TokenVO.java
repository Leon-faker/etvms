package com.lihang.etvms.adapter.controller.response.token;

/**
 * Token返回
 *
 * @date 2022/12/9
 **/
public class TokenVO {

    /**
     * Token
     */
    private String token;

    public static TokenVO of(String token) {
        TokenVO vo = new TokenVO();
        vo.setToken(token);
        return vo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
