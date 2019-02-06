package pers.illiant.yummy.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.illiant.yummy.dao.MemberMapper;
import pers.illiant.yummy.entity.Member;
import pers.illiant.yummy.service.MemberService;

import java.util.List;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public List<Member> findAll() {
        return memberMapper.selectAll();
    }

    @Override
    public boolean signin(String username, String password) {
        Member member = memberMapper.selectByName(username);
        if (member.getMemberPassword().equals(password)) {
            System.out.println("password correct");
            return true;
        } else {
            System.out.println("password wrong");
            return false;
        }
    }

    @Override
    public boolean signup(String name, String email, String password) {

        Member member = new Member();
        member.setEmail(email);
        member.setMemberName(name);
        member.setMemberPassword(password);

        try {
            memberMapper.insert(member);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Member findByName(String name) {
        return memberMapper.selectByName(name);
    }

    @Override
    public Member findById(int id) {
        return memberMapper.selectByPrimaryKey(id);
    }


}
