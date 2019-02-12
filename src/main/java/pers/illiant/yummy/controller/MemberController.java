package pers.illiant.yummy.controller;

import net.sf.json.JSONObject;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import pers.illiant.yummy.entity.Address;
import pers.illiant.yummy.entity.Member;
import pers.illiant.yummy.model.MemberVO_login;
import pers.illiant.yummy.model.MemberVO_post;
import pers.illiant.yummy.model.MemberVO_signup;
import pers.illiant.yummy.service.serviceImpl.AuthenticationService;
import pers.illiant.yummy.service.serviceImpl.MemberServiceImpl;
import pers.illiant.yummy.util.Result;

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

    @RequestMapping("/getMemberInfo")
    public Result getMemberInfo(@RequestParam Integer memberId) {
        return memberService.getMemberInfo(memberId);
    }

    @RequestMapping("/modifyAddress")
    public Result modifyAddress(@RequestBody Address address) {
        return memberService.modifyAddress(address);
    }

    @RequestMapping("/modifyInfo")
    public Result modifyInfo(@RequestBody MemberVO_post member) {

        return memberService.modifyInfo(member);
    }

    @RequestMapping("/addAddress")
    public Result addAddress(@RequestBody Address address) {
        return memberService.addAddress(address);
    }

    @RequestMapping("/getAddress")
    public Result getAddress(@RequestParam Integer memberId) {

        return memberService.getAddress(memberId);
    }

    @RequestMapping("/getMemberLevel")
    public Result getMemberLevel(@RequestParam Integer memberId) {
        return memberService.getMemberLevel(memberId);
    }
}
