package com.lihang.etvms.adapter.controller;

import com.lihang.etvms.adapter.controller.request.login.LoginRequestParam;
import com.lihang.etvms.adapter.controller.request.user.ModificationPasswordParam;
import com.lihang.etvms.adapter.controller.request.user.ResetPasswordParam;
import com.lihang.etvms.adapter.controller.request.user.UserListParam;
import com.lihang.etvms.adapter.controller.request.user.UserParam;
import com.lihang.etvms.adapter.controller.response.PageableResponse;
import com.lihang.etvms.adapter.controller.response.Response;
import com.lihang.etvms.adapter.controller.response.menu.MenuVO;
import com.lihang.etvms.adapter.controller.response.token.TokenVO;
import com.lihang.etvms.adapter.controller.response.user.UserVO;
import com.lihang.etvms.constant.RequestFilterConstant;
import com.lihang.etvms.domain.role.Role;
import com.lihang.etvms.domain.user.User;
import com.lihang.etvms.domain.user.UserService;
import com.lihang.etvms.infrastructure.utils.UserSecurityContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户管理接口
 *
 * @date 2022/12/7
 * @order 0
 **/
@RestController
@RequestMapping(path = "/user")
public class UserController{

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 根据用户ID查询用户
     *
     * @param userId 用户ID | 1
     * @return 用户 {@link UserVO}
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": {
     * "userId": 2,
     * "username": "wyx",
     * "customerName": "王玉玺",
     * "active": true,
     * "roles": [],
     * "createTime": "2023-01-03 11:31:34",
     * "updateTime": "2023-01-03 11:31:34"
     * }
     * }
     */
    @GetMapping(path = "/{userId}")
    public Response<UserVO> findById(@PathVariable(name = "userId") final Long userId) {
        return Response.success(UserVO.of(this.userService.findById(userId)));
    }

    /**
     * 根据用户代码查询用户
     *
     * @param username 用户代码
     * @return 用户 {@link UserVO}
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": {
     * "userId": 2,
     * "username": "wyx",
     * "customerName": "王玉玺",
     * "active": true,
     * "roles": [],
     * "createTime": "2023-01-03 11:31:34",
     * "updateTime": "2023-01-03 11:31:34"
     * }
     * }
     */

    @GetMapping
    public Response<UserVO> findByUserName(@RequestParam String username) {
        return Response.success(UserVO.of(this.userService.findByUsername(username)));
    }

    /**
     * 登录
     *
     * @param param 登录参数 {@link LoginRequestParam}
     * @return 登录凭证 {@link TokenVO}
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": {
     * "token":"This is login token"
     * }
     * }
     */
    @PostMapping(path = "/login")
    public Response<TokenVO> login(@RequestBody @Validated LoginRequestParam param) {
        String token = this.userService.login(param.getUsername(), param.getPassword());
        return Response.success(TokenVO.of(token));
    }

    /**
     * 获取当前用户所需要加载的菜单列表
     *
     * @return 菜单列表 {@link MenuVO}
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": [
     * {
     * "id": 1,
     * "name": "权限管理",
     * "code": "",
     * "parentId": null,
     * "icon": "md-person"
     * }
     * ]
     * }
     */
    @GetMapping(path = "/menus")
    public Response<List<MenuVO>> findUserMenus() {
        final Long current = UserSecurityContext.get();
        User user = this.userService.findById(current);
        return Response.success(user.menus().stream().map(MenuVO::of).sorted(Comparator.comparing(MenuVO::getId)).collect(Collectors.toList()));
    }

    /**
     * 获取所有用户列表
     *
     * @return 用户列表 {@link UserVO}
     * @response {
     * "code": "0",
     * "msg": "0",
     * "data":[
     * {
     * "userId": 2,
     * "username": "wyx",
     * "customerName": "王玉玺",
     * "active": true,
     * "roles": [],
     * "createTime": "2023-01-03 11:31:34",
     * "updateTime": "2023-01-03 11:31:34"
     * }
     * ]
     * }
     */
    @GetMapping("/list")
    public Response<List<UserVO>> findList() {
        return Response.success(this.userService.findList().stream().map(UserVO::of).collect(Collectors.toList()));
    }

    /**
     * 获取用户列表
     *
     * @return 用户列表 {@link UserVO}
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": {
     * "total": 2,
     * "pageSize": 10,
     * "pageNumber": 1,
     * "dataList": [
     * {
     * "userId": 1,
     * "username": "dyq",
     * "customerName": "杜亚泉",
     * "active": true,
     * "roles": [
     * {
     * "id":1,
     * "name":"系统管理员"
     * }
     * ],
     * "createTime": "2022-12-16 16:12:42",
     * "updateTime": "2022-12-16 16:12:42"
     * },
     * {
     * "userId": 2,
     * "username": "wyx",
     * "customerName": "王玉玺",
     * "active": true,
     * "roles": [],
     * "createTime": "2023-01-03 11:31:34",
     * "updateTime": "2023-01-03 11:31:34"
     * }
     * ]
     * }
     * }
     */
    @PostMapping
    public Response<PageableResponse<UserVO>> findList(@RequestBody UserListParam userListParam) {
        PageableResponse<User> users = this.userService.findList(userListParam.getUsername(), userListParam.getPageSize(), userListParam.getPageNumber());
        return Response.success(PageableResponse.of(users.getTotal(), users.getPageSize(), users.getPageNumber(), users.getDataList().stream().map(UserVO::of).collect(Collectors.toList())));
    }


    /**
     * 获取当前用户信息
     *
     * @return 用户信息
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": {
     * "userId": 2,
     * "username": "wyx",
     * "customerName": "wyx",
     * "active": true,
     * "roles": [],
     * "createTime": "2023-01-03 11:31:34",
     * "updateTime": "2023-01-03 11:31:34"
     * }
     * }
     */
    @GetMapping(path = "/current")
    public Response<UserVO> current() {
        User user = this.userService.findById(UserSecurityContext.get());
        return Response.success(UserVO.of(user));
    }

    /**
     * 新增用户
     *
     * @param param 用户参数
     * @return 用户信息
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": null
     * }
     */
    @PutMapping
    public Response<String> add(@RequestBody UserParam param) {
        final Long operator = UserSecurityContext.get();
        final List<Role> roles = param.getRoles().stream().map(e -> new Role(e.getId(), e.getName(), Collections.emptyList())).collect(Collectors.toList());
        this.userService.add(param.getUsername(), param.getPassword(), param.isActive(), param.getCustomerName(), roles, operator);
        return Response.success(null);
    }

    /**
     * 修改用户信息
     *
     * @param param  用户参数
     * @param userId 用户ID，整数型 |1
     * @return 用户信息
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": null
     * }
     */
    @PostMapping(path = "/{userId}")
    public Response<String> update(@RequestBody UserParam param, @PathVariable(value = "userId") final Long userId) {
        final Long operator = UserSecurityContext.get();
        final List<Role> roles = param.getRoles().stream().map(e -> new Role(e.getId(), e.getName(), Collections.emptyList())).collect(Collectors.toList());
        this.userService.update(userId, param.getCustomerName(), param.isActive(), roles, operator);
        return Response.success(null);
    }

    /**
     * 删除用户
     *
     * @param userId 用户ID，整数型 |1
     * @return ok
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": null
     * }
     */
    @DeleteMapping(path = "/{userId}")
    public Response<String> delete(@PathVariable(value = "userId") final Long userId) {
        this.userService.delete(userId);
        return Response.success(null);
    }

    /**
     * 激活用户
     *
     * @param userId 用户ID，整数型 |1
     * @return 用户信息
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": null
     * }
     */
    @PostMapping(path = "/{userId}/enable")
    public Response<String> enable(@PathVariable(value = "userId") final Long userId) {
        final Long operator = UserSecurityContext.get();
        this.userService.enable(userId, operator);
        return Response.success(null);
    }

    /**
     * 禁用用户
     *
     * @param userId 用户ID，整数型 |1
     * @return 用户信息
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": null
     * }
     */
    @PostMapping(path = "/{userId}/disable")
    public Response<UserVO> disable(@PathVariable(value = "userId") final Long userId) {
        final Long operator = UserSecurityContext.get();
        this.userService.disable(userId, operator);
        return Response.success(null);
    }

    /**
     * 退出登录
     *
     * @param token Token
     * @return ok
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": null
     * }
     */
    @DeleteMapping(path = "/logout")
    public Response<String> logout(@RequestHeader(value = RequestFilterConstant.REQUEST_AUTHORIZATION_PREFIX) String token) {
        final Long userId = UserSecurityContext.get();
        token = token.trim().substring(RequestFilterConstant.TOKEN_PREFIX.length());
        this.userService.logout(token, userId);
        return Response.success(null);
    }

    /**
     * 修改密码
     *
     * @param param 修改密码参数
     * @return ok
     * @apiNote 修改当前用户自己的密码
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": null
     * }
     */
    @PostMapping(path = "/modification")
    public Response<String> modification(@RequestBody ModificationPasswordParam param) {
        final Long current = UserSecurityContext.get();
        User user = this.userService.findById(current);
        this.userService.modification(user, param.getOldPassword(), param.getNewPassword());
        return Response.success(null);
    }

    /**
     * 重置密码
     *
     * @param param  重置密码参数
     * @param userId 用户ID，整数型 | 1
     * @return ok
     * @apiNote 重置其他用户密码
     * @response {
     * "code": "0",
     * "msg": "0",
     * "detail": "",
     * "body": null
     * }
     */
    @PostMapping(path = "/{userId}/reset")
    public Response<String> reset(@RequestBody ResetPasswordParam param, @PathVariable(value = "userId") final Long userId) {
        final Long operator = UserSecurityContext.get();
        this.userService.reset(userId, param.getPassword(), operator);
        return Response.success(null);
    }
}
