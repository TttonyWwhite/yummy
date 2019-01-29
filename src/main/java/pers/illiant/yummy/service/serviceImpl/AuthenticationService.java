package pers.illiant.yummy.service.serviceImpl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;
import pers.illiant.yummy.entity.Member;

import java.io.UnsupportedEncodingException;

@Service
public class AuthenticationService {
    public String getToken(Member member) {
        String token;
        token = JWT.create()
                .withAudience(member.getMemberId().toString()) //将member_id 保存到token里
                .sign(Algorithm.HMAC256(member.getMemberPassword())); // 以password作为token的密钥

        return token;
    }
}
