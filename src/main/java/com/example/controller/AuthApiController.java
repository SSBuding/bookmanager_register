package com.example.controller;

import com.example.entity.resp.RestBean;
import com.example.service.AccountService;
import com.example.service.VerifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

@Api(tags = "用户验证")
@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    @Resource
    VerifyService verifyService;
    @Resource
    AccountService accountService;

    @ApiOperation("请求验证码")
    @GetMapping("/verify-code")
    public RestBean<Void> verifyCode(@ApiParam("邮箱地址") @RequestParam("email") String email){
        try {
            verifyService.sendVerifyCode(email);
            return new RestBean<>(200,"邮件发送成功!");
        }catch (Exception e){
            return new RestBean<>(500,"邮件发送失败!");

        }

    }
    @ApiOperation("发起注册请求")
    @PostMapping(value = "/register")
    public RestBean<Void> register(String username, String password, String email, String verify){
        if(verifyService.doVerify(email,verify)){
            accountService.createAccount(username,password);
            return new RestBean<>(200,"注册成功!");
        }else {
            return new RestBean<>(403,"注册失败,验证码填写错误!");
        }

    }
    @PostMapping("login-success")
    public RestBean<Void> loginSuccess(){
        return new RestBean<>(200,"登录成功");
    }
    @GetMapping("logout-success")
    public RestBean<Void> logoutSuccess(){
        return new RestBean<>(200,"退出成功");
    }
    @PostMapping("login-failure")
    public RestBean<Void> loginFailure(){
        return new RestBean<>(304,"登录失败,用户名或密码错误!");
    }

    @ApiIgnore
    @RequestMapping("/access-deny")
    public RestBean<Void> accessDeny(){
        return new RestBean<>(401,"未验证,请先进行登录!");
    }

}
