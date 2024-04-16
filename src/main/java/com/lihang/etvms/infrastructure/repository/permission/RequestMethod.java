package com.lihang.etvms.infrastructure.repository.permission;

/**
 * HTTP 请求方法
 *
 * @date 2022/12/30
 */
public enum RequestMethod {

    /**
     * GET
     */
    GET(1, "GET"),

    /**
     * HEAD
     */
    HEAD(2, "HEAD"),

    /**
     * POST
     */
    POST(3, "POST"),

    /**
     * PUT
     */
    PUT(4, "PUT"),

    /**
     * PATCH
     */
    PATCH(5, "PATCH"),

    /**
     * DELETE
     */
    DELETE(6, "DELETE"),

    /**
     * OPTIONS
     */
    OPTIONS(7, "OPTIONS"),

    /**
     * TRACE
     */
    TRACE(8, "TRACE");

    private final int code;
    private final String name;

    RequestMethod(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static RequestMethod codeOf(Integer code) {
        if (null == code) {
            return null;
        }
        for (RequestMethod value : RequestMethod.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }

    public static RequestMethod nameOf(String name) {
        if (null == name || "".equals(name)) {
            return null;
        }
        for (RequestMethod value : RequestMethod.values()) {
            if (value.getName().equals(name)) {
                return value;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
