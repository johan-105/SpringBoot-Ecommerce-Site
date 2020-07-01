package com.johann.ecommerce.controller;

import com.johann.ecommerce.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Api(tags = "UmsMemberController", description = "会员登录注册管理")
public class UmsMemberController {

    private final UmsMemberService umsMemberService;

    @Autowired
    public UmsMemberController(UmsMemberService umsMemberService) {
        this.umsMemberService = umsMemberService;
    }

    @ApiOperation("获取验证码")
    @GetMapping
    public ResponseEntity<String> getAuthCode(@RequestParam String phone) {
        return ResponseEntity.ok(umsMemberService.generateAuthCode(phone));
    }

    @ApiOperation("判断验证码是否正确")
    @PostMapping
    public ResponseEntity<Void> verifyAuthCode(@RequestParam String phone, @RequestParam String authCode) {
        if (umsMemberService.verifyAuthCode(phone, authCode)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
