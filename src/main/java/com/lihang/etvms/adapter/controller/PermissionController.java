package com.lihang.etvms.adapter.controller;

import com.lihang.etvms.adapter.controller.response.Response;
import com.lihang.etvms.adapter.controller.response.permission.PermissionVO;
import com.lihang.etvms.domain.permission.Permission;
import com.lihang.etvms.domain.permission.PermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限接口
 *
 * @date 2023/1/3
 * @order 2
 **/
@RestController
@RequestMapping(path = "/permission")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * 重新加载权限缓存
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
        this.permissionService.reload();
        return Response.success();
    }

    /**
     * 获取所有权限列表
     *
     * @return 权限列表 {@link PermissionVO}
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": [
     * {
     * "id":1,
     * "url":"/user/menu",
     * "description":"基本权限",
     * "method":"GET",
     * "menuName":"权限管理"
     * }
     * ]
     * }
     */
    @GetMapping
    public Response<List<PermissionVO>> findAll() {
        List<Permission> permissions = this.permissionService.findAll();
        return Response.success(permissions.stream().map(PermissionVO::of).collect(Collectors.toList()));
    }
}
