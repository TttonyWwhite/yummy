package pers.illiant.yummy.service;

import org.springframework.stereotype.Service;
import pers.illiant.yummy.entity.Member;


@Service("memberService")
public interface MemberService {

    boolean signin(String username, String password);

    boolean signup(String name, String email, String password);


}
