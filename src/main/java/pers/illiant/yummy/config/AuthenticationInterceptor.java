package pers.illiant.yummy.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import pers.illiant.yummy.annotation.LoginRequired;
import pers.illiant.yummy.entity.Member;
import pers.illiant.yummy.service.MemberService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Resource
    MemberService memberService;


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod))
            return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        //判断接口是否需要登陆
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        //有注解，需要认证
        if (methodAnnotation != null) {
            String token = request.getHeader("token");
            if (token == null)
                throw new RuntimeException("无token，请重新登录");

            int memberId;

            try {
                memberId = Integer.parseInt(JWT.decode(token).getAudience().get(0)); //获取token中的id
            } catch (JWTDecodeException e) {
                throw new RuntimeException("token无效，请重新登陆");
            }

            Member member = memberService.findById(memberId);
            if (member == null) {
                throw new RuntimeException("用户不存在，请重新登陆");
            }

            //验证token
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(member.getMemberPassword())).build();
            try {
                verifier.verify(token);
            } catch (JWTVerificationException e) {
                throw new RuntimeException("token无效，请重新登录");
            }

            request.setAttribute("currentUser", member);
            return true;
        }

        return true;

    }
}
