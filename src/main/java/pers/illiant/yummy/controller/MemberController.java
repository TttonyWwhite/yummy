package pers.illiant.yummy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import pers.illiant.yummy.dao.MemberMapper;
import pers.illiant.yummy.entity.Address;
import pers.illiant.yummy.model.MemberVO_login;
import pers.illiant.yummy.model.MemberVO_post;
import pers.illiant.yummy.model.MemberVO_signup;
import pers.illiant.yummy.service.serviceImpl.MemberServiceImpl;
import pers.illiant.yummy.util.Result;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import pers.illiant.yummy.util.ResultUtils;

import java.io.IOException;

@RestController
@EnableAutoConfiguration
public class MemberController {
    @Resource
    private MemberServiceImpl memberService;

    @RequestMapping("/login")
    public Result login(@RequestBody MemberVO_login member) {
        return memberService.signin(member);
    }

    @RequestMapping("/signup")
    public Result signup(@RequestBody MemberVO_signup member) {
       if (memberService.signup(member.getName(), member.getEmail(), member.getPassword()))
           return ResultUtils.success();
       else
           return ResultUtils.error(11117, "注册失败");
    }

    @RequestMapping(value = "activation/{memberId}", method = RequestMethod.GET)
    public void activation(@PathVariable Integer memberId, HttpServletResponse response) throws IOException {
        if (memberService.activation(memberId)) {
            response.sendRedirect("http://127.0.0.1:9099/#/homepage/" + memberId);
        }
    }

    @RequestMapping("/getMemberInfo")
    public Result getMemberInfo(@RequestParam Integer memberId) {
        return memberService.getMemberInfo(memberId);
    }

    @RequestMapping("/getBalance")
    public Result getBalance(@RequestParam Integer memberId) {
        return memberService.getBalance(memberId);
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

    @RequestMapping("/deleteAddress")
    public Result deleteAddress(@RequestParam Integer addressId) {
        return memberService.deleteAddress(addressId);
    }

    @RequestMapping("/getAddress")
    public Result getAddress(@RequestParam Integer memberId) {

        return memberService.getAddress(memberId);
    }

    @RequestMapping("/getMemberLevel")
    public Result getMemberLevel(@RequestParam Integer memberId) {
        return memberService.getMemberLevel(memberId);
    }

    /**
     * 用户注销api
     * @param memberId 要注销的用户ID
     * @return 注销操作结果
     */
    @RequestMapping("/writeOff")
    public Result writeOff(@RequestParam Integer memberId) {
        return memberService.writeOff(memberId);
    }


}
