package pers.illiant.yummy.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.illiant.yummy.model.MemberVO_login;
import pers.illiant.yummy.model.MemberVO_signup;
import pers.illiant.yummy.service.serviceImpl.MemberServiceImpl;

import javax.annotation.Resource;

@RestController
@EnableAutoConfiguration
public class MemberController {
    @Resource
    private MemberServiceImpl memberService;

    @RequestMapping("/login")
    public boolean login(@RequestBody MemberVO_login member) {
        return memberService.signin(member.getName(), member.getPassword());
    }

    @RequestMapping("/signup")
    public boolean signup(@RequestBody MemberVO_signup member) {
        System.out.println(member.getName());
        return memberService.signup(member.getName(), member.getEmail(), member.getPassword());
    }

}
