package pers.illiant.yummy.controller;

import net.sf.json.JSONObject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.illiant.yummy.annotation.LoginRequired;
import pers.illiant.yummy.entity.Member;
import pers.illiant.yummy.model.MemberVO_login;
import pers.illiant.yummy.model.MemberVO_signup;
import pers.illiant.yummy.service.serviceImpl.AuthenticationService;
import pers.illiant.yummy.service.serviceImpl.MemberServiceImpl;

import javax.annotation.Resource;

@RestController
@EnableAutoConfiguration
public class MemberController {
    @Resource
    private MemberServiceImpl memberService;

    @Resource
    AuthenticationService authenticationService;

    @RequestMapping("/login")
    public Object login(@RequestBody MemberVO_login member) {
        //return memberService.signin(member.getName(), member.getPassword());
        Member memberInDatabase = memberService.findByName(member.getName());
        JSONObject jsonObject = new JSONObject();
        if (memberInDatabase == null) {
            jsonObject.put("message", "用户不存在");
        } else if ( !member.getPassword().equals(memberInDatabase.getMemberPassword())) {
            jsonObject.put("message", "密码错误");
        } else {
            String token = authenticationService.getToken(memberInDatabase);
            jsonObject.put("token", token);
            jsonObject.put("member", memberInDatabase );
        }

        return jsonObject;
    }

    @RequestMapping("/signup")
    public boolean signup(@RequestBody MemberVO_signup member) {
        System.out.println(member.getName());
        return memberService.signup(member.getName(), member.getEmail(), member.getPassword());
    }

    @LoginRequired
    @GetMapping("/test")
    public Object testLogin() {
        return "success";
    }

}
