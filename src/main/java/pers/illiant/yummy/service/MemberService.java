package pers.illiant.yummy.service;

import org.springframework.stereotype.Service;
import pers.illiant.yummy.entity.Address;
import pers.illiant.yummy.entity.Member;
import pers.illiant.yummy.model.MemberVO_post;
import pers.illiant.yummy.util.Result;


@Service("memberService")
public interface MemberService {

    boolean signin(String username, String password);

    boolean signup(String name, String email, String password);

    Member findByName(String name);

    Member findById(int id);

    Result getMemberInfo(int memberId);

    Result modifyAddress(Address address);

    Result modifyInfo(MemberVO_post memberVO_post);

    Result addAddress(Address address);
}
