package pers.illiant.yummy.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.illiant.yummy.dao.AddressMapper;
import pers.illiant.yummy.dao.MemberMapper;
import pers.illiant.yummy.entity.Address;
import pers.illiant.yummy.entity.Member;
import pers.illiant.yummy.model.MemberVO_post;
import pers.illiant.yummy.service.MemberService;
import pers.illiant.yummy.util.Result;
import pers.illiant.yummy.util.ResultUtils;

import javax.swing.text.Style;
import java.util.ArrayList;
import java.util.List;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private AddressMapper addressMapper;

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

    @Override
    public Result getMemberInfo(int memberId) {
        Member member = memberMapper.selectByPrimaryKey(memberId);
        MemberVO_post post = new MemberVO_post();
        post.setMemberId(memberId);
        post.setMemberName(member.getMemberName());
        post.setPhoneNumber(member.getPhoneNumber());
        List<Address> addresses = addressMapper.selectByMemberId(memberId);
        post.setAddresses(addresses);
        return ResultUtils.success(post);
    }

    @Override
    public Result modifyAddress(Address address) {
        try {
            addressMapper.updateByPrimaryKey(address);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.error(11111, "地址更新失败");
        }

        return ResultUtils.success();
    }

    @Override
    public Result modifyInfo(MemberVO_post memberVO_post) {
        Member member = new Member();
        member.setMemberId(memberVO_post.getMemberId());
        member.setMemberName(memberVO_post.getMemberName());
        member.setPhoneNumber(memberVO_post.getPhoneNumber());
        try {
            memberMapper.modifyInfo(member);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.error(11112, "用户信息更新失败");
        }

        return ResultUtils.success();
    }

    @Override
    public Result addAddress(Address address) {
        try {
            addressMapper.insert(address);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.error(11113, "增加地址失败");
        }

        return ResultUtils.success();
    }

    @Override
    public Result getAddress(int memberId) {
        List<Address> list = new ArrayList<>();
        Member member = memberMapper.selectByPrimaryKey(memberId);
        try {
            list = addressMapper.selectByMemberId(memberId);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.error(11114, "查询地址失败");
        }

        //默认地址要放在地址列表的第一个
        List<String> strList = new ArrayList<>();
        strList.add(member.getDefaultAddress());
        for (Address item : list) {
            if (!item.getAddress().equals(member.getDefaultAddress())) //不要重复添加默认地址
                strList.add(item.getAddress());
        }

        return ResultUtils.success(strList);
    }


}
