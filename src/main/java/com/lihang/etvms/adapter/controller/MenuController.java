package com.lihang.etvms.adapter.controller;

import com.lhhj.hft.common.response.Response;
import com.lhhj.hft.user.domain.menu.MenuService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单接口
 *
 * @date 2023/1/4
 * @order 999
 **/
@RestController
@RequestMapping(path = "/menu")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 重新加载菜单缓存
     *
     * @return ok
     * @apiNote 内部接口，不对外暴露
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": null
     * }
     */
    @PostMapping
    public Response<String> reloadCache() {
        this.menuService.reload();
        return Response.success();
    }
}
