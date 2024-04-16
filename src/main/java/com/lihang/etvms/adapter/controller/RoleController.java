package com.lihang.etvms.adapter.controller;

import com.lihang.etvms.adapter.controller.request.role.RoleListParam;
import com.lihang.etvms.adapter.controller.request.role.RoleParam;
import com.lihang.etvms.adapter.controller.response.PageableResponse;
import com.lihang.etvms.adapter.controller.response.Response;
import com.lihang.etvms.adapter.controller.response.role.RoleVO;
import com.lihang.etvms.domain.permission.Permission;
import com.lihang.etvms.domain.role.Role;
import com.lihang.etvms.domain.role.RoleService;
import com.lihang.etvms.infrastructure.repository.permission.RequestMethod;
import com.lihang.etvms.infrastructure.utils.UserSecurityContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色管理接口
 *
 * @date 2023/1/3
 * @order 1
 **/
@RestController
@RequestMapping(path = "/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * 获取角色列表
     *
     * @return 角色列表 {@link RoleVO}
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": [
     * {
     * "id": 1,
     * "name": "系统管理员"
     * }
     * ]
     * }
     */
    @PostMapping
    public Response<PageableResponse<RoleVO>> findList(@RequestBody RoleListParam roleListParam) {
        PageableResponse<Role> rolePageableResponse = this.roleService.findList(roleListParam.getRoleName(), roleListParam.getPageSize(), roleListParam.getPageNumber());
        return Response.success(PageableResponse.of(rolePageableResponse.getTotal(), rolePageableResponse.getPageSize(), rolePageableResponse.getPageNumber(), rolePageableResponse.getDataList().stream().map(RoleVO::of).collect(Collectors.toList())));
    }

    /**
     * 获取所有角色列表
     *
     * @return 角色列表 {@link RoleVO}
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": [
     * {
     * "id": 1,
     * "name": "系统管理员"
     * }
     * ]
     * }
     */
    @GetMapping
    public Response<List<RoleVO>> findAll() {
        List<Role> roles = this.roleService.findAll();
        return Response.success(roles.stream().map(RoleVO::of).collect(Collectors.toList()));
    }

    /**
     * 新增角色
     *
     * @param roleParam 角色参数 {@link RoleParam}
     * @return ok
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": null
     * }
     */
    @PutMapping
    public Response<String> add(@RequestBody RoleParam roleParam) {
        final Long operator = UserSecurityContext.get();
        final String roleName = roleParam.getName();
        final List<Permission> permissions = roleParam.getPermissions().stream().map(e -> new Permission(e.getId(), e.getUrl(), e.getDescription(), null, RequestMethod.nameOf(e.getMenuName()))).collect(Collectors.toList());
        this.roleService.add(roleName, permissions, operator);
        return Response.success();
    }

    /**
     * 修改角色信息
     *
     * @param roleParam 角色参数 {@link RoleParam}
     * @param roleId    角色ID，整数型 | 1
     * @return ok
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": null
     * }
     */
    @PostMapping(path = "/{roleId}")
    public Response<String> update(@RequestBody RoleParam roleParam, @PathVariable(value = "roleId") final Long roleId) {
        final Long operator = UserSecurityContext.get();
        final List<Permission> permissions = roleParam.getPermissions().stream().map(e -> new Permission(e.getId(), e.getUrl(), e.getDescription(), null, RequestMethod.nameOf(e.getMethod()))).collect(Collectors.toList());
        this.roleService.update(roleId, roleParam.getName(), permissions, operator);
        return Response.success();
    }

    /**
     * 根据角色ID删除角色
     *
     * @param roleId 角色ID
     * @return ok
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": null
     * }
     */
    @DeleteMapping(path = "/{roleId}")
    public Response<String> delete(@PathVariable(value = "roleId") final Long roleId) {
        this.roleService.delete(roleId);
        return Response.success();
    }
}
