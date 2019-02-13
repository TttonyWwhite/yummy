package pers.illiant.yummy.service;

import org.springframework.stereotype.Service;
import pers.illiant.yummy.entity.Address;
import pers.illiant.yummy.entity.Member;
import pers.illiant.yummy.model.MemberVO_login;
import pers.illiant.yummy.model.MemberVO_post;
import pers.illiant.yummy.util.Result;


@Service("memberService")
public interface MemberService {

    Result signin(MemberVO_login member);

    boolean signup(String name, String email, String password);

    Member findByName(String name);

    Member findById(int id);

    Result getMemberInfo(int memberId);

    Result modifyAddress(Address address);

    Result modifyInfo(MemberVO_post memberVO_post);

    Result addAddress(Address address);

    Result getAddress(int memberId);

    Result getMemberLevel(int memberId);

    /**
     * 用户注销
     */
    Result writeOff(int memberId);

    /**
     * 用于验证此用户是否为已注册待激活用户
     * @param memberId 用户id
     */
    boolean activation(int memberId);

}
