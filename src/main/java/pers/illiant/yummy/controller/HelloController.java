package pers.illiant.yummy.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pers.illiant.yummy.service.serviceImpl.MemberServiceImpl;

import javax.annotation.Resource;

@RestController
@EnableAutoConfiguration
public class HelloController {

    @Resource(name = "memberService")
    private MemberServiceImpl memberService;

    @RequestMapping("/hello")
    @ResponseBody
    private String index() {

        System.out.println(memberService.signin("illiant", "123"));
        return "Hello";
    }

}
